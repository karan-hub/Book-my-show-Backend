package com.cfs.bookMyShow.service;

import com.cfs.bookMyShow.dto.*;
import com.cfs.bookMyShow.exception.SeatNotAvailableException;
import com.cfs.bookMyShow.exception.ShowNotFoundException;
import com.cfs.bookMyShow.exception.UserNotFoundException;
import com.cfs.bookMyShow.model.*;
import com.cfs.bookMyShow.model.type.BookingStatus;
import com.cfs.bookMyShow.model.type.PaymentStatus;
import com.cfs.bookMyShow.model.type.SeatStatus;
import com.cfs.bookMyShow.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Service;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BookingService {

    @Autowired
    private  final UserRepository userRepository ;

    @Autowired
    private  final ShowRepository showRepository ;

    @Autowired
    private  final ShowSeatRepository showSeatRepository ;

    @Autowired
    private  final BookingRepository  bookingRepository ;

    @Autowired
    private  final PaymentRepository paymentRepository ;

    @Value("${app.gst.rate}")
    private   Double GST;



    @Transactional
    public  BookingDTO createBooking(RequestBookingDTO dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(()->  new  UserNotFoundException("USER NOT FOUND"));

        Show show =  showRepository.findById(dto.getShowId())
                .orElseThrow(()-> new ShowNotFoundException("SHOW NOT FOUND"));

//        List<ShowSeat> Seats =  showSeatRepository.findAllById(dto.getSeatIds());
        List<ShowSeat> showSeats = showSeatRepository.findByShowIdAndSeatIdIn(dto.getShowId(), dto.getSeatIds());
        if (showSeats.isEmpty())  throw new SeatNotAvailableException("THE SEATS IS EMPTY");
        System.out.println("ENTER \n\n\n");
        showSeats.forEach(
                seat-> {
                    if (seat.getStatus() != SeatStatus.AVAILABLE)
                        throw new SeatNotAvailableException(
                                "Seat " + seat.getSeat().getId() + " is not available"
                        );
                    System.out.println(seat.getPrice() +" THIS THIS THIS" + seat);
                    seat.setStatus(SeatStatus.LOCKED);
                }
        );



 java.lang.Double  amount =  showSeats.stream()
                .mapToDouble(
                        ShowSeat::getPrice
                ).sum();


        System.out.println( "payment boss "+amount);
        Double finalAmount = amount * (1 + GST / 100);


        Payment payment = new Payment();
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setPaymentTime(LocalDateTime.now());
        payment.setPaymentMethod(dto.getPaymentMethod());
        String txnId = "TXN-" + user.getId() + "-" + System.currentTimeMillis();
        payment.setTransactionId(txnId);
        payment.setAmount(amount);
        payment.setGST(GST);
        payment.setTotalAmount(finalAmount);
        paymentRepository.save(payment);
        Booking  booking = new Booking();

        booking.setBookingTime(LocalDateTime.now());
        booking.setStatus(BookingStatus.CONFIRM);
        booking.setShow(show);
        booking.setPayment(payment);
        booking.setShowSeats(showSeats);
        booking.setUser(user);
        booking.setTotalAmount(finalAmount);
        booking.setBookingNumber("BM"+user.getId()+System.currentTimeMillis()% 100000);

        Booking saveBooking = bookingRepository.save(booking);
        showSeats.forEach(
                seat-> {
                    seat.setStatus(SeatStatus.BOOKED);
                    seat.setBooking(booking);
                }
        );
        showSeatRepository.saveAll(showSeats);
        return mapToDto(saveBooking, showSeats);
    }

    public  List<String> getAllBookingNumbers(){
        List<String> bookings =  bookingRepository.findAllBookingNumbers();
        return  bookings;
    }

    private BookingDTO mapToDto(Booking booking , List<ShowSeat> showSeats ) {

        UserDTO userDTO =  new UserDTO();
        userDTO.setId(booking.getUser().getId());
        userDTO.setName(booking.getUser().getName());
        userDTO.setEmail(booking.getUser().getEmail());
        userDTO.setNumber(booking.getUser().getPhoneNumber());

        ShowDTO showDTO = new ShowDTO();
        Show show = booking.getShow();
        showDTO.setId(show.getId());
        showDTO.setEndTime(show.getEndTime());
        showDTO.setStartTime(show.getStartTime());
        showDTO.setScreenId(show.getScreen().getId());

        ShowDTO showDto=new ShowDTO();
        showDto.setId(booking.getShow().getId());
        showDto.setStartTime(booking.getShow().getStartTime());
        showDto.setEndTime(booking.getShow().getEndTime());
        showDTO.setMovieId(booking.getShow().getMovie().getId());

        MovieDTO movieDto = new MovieDTO();
        Movie movie=booking.getShow().getMovie();
        movieDto.setId(movie.getId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setLanguage(movie.getLanguage());
        movieDto.setGenre(movie.getGenre());
        movieDto.setDuration(movie.getDuration());
        movieDto.setPosterUrl(movie.getPosterUrl());

        ScreenDTO screenDto=new ScreenDTO();
        screenDto.setId(booking.getShow().getScreen().getId());
        screenDto.setName(booking.getShow().getScreen().getName());
        showDTO.setScreenId(screenDto.getId());


        Double actualAmount = booking.getTotalAmount() * 1.0 / 1.18;
        System.out.println(actualAmount);


        BookingDTO  bookingDTO =  new BookingDTO();
        bookingDTO.setBookingNumber(booking.getBookingNumber());
        bookingDTO.setBookingTime(booking.getBookingTime());
       bookingDTO.setStatus(String.valueOf(booking.getPayment().getStatus()));
        bookingDTO.setTotalAmount(Math.round(booking.getTotalAmount()*100.0)/100.0);
        bookingDTO.setUser(userDTO);
        bookingDTO.setShow(showDTO);
        bookingDTO.setMovie(movieDto);

        CityDTO cityDTO = new CityDTO();
        cityDTO.setName("pune");

        TheaterDTO theaterDto=new TheaterDTO();
        theaterDto.setId(bookingDTO.getShow().getScreenId());
        Theater  theater = booking.getShow().getScreen().getTheater();
        theaterDto.setName(theater.getName());
        theaterDto.setCity(cityDTO);
        theaterDto.setTotalScreens(theater.getTotalScreens());

        screenDto.setTheater(theaterDto);

        List<ShowSeatDTO> showSeatDto=showSeats.stream()
                .map(seat->{
                    ShowSeatDTO seatDto=new ShowSeatDTO();
                    seatDto.setId(seat.getId());

                    SeatDTO baseSeatDto=new SeatDTO();
                    baseSeatDto.setId(seat.getSeat().getId());
                    baseSeatDto.setSeatNumber(seat.getSeat().getSeatNumber());
                    baseSeatDto.setSeatType(seat.getSeat().getSeatType());
                    baseSeatDto.setBasePrice(seat.getSeat().getBasePrice());

                    seatDto.setSeat(baseSeatDto);

                    return seatDto;
                }).toList();

        if (booking.getPayment() != null) {
            PaymentDTO paymentDTO = new PaymentDTO();
            paymentDTO.setBookingId(booking.getBookingNumber());
            paymentDTO.setPaymentMethod(booking.getPayment().getPaymentMethod());
            paymentDTO.setStatus(booking.getStatus());
            paymentDTO.setPaymentTime(booking.getPayment().getPaymentTime());
            paymentDTO.setTransactionId(booking.getPayment().getTransactionId());
            paymentDTO.setActualAmount((double) Math.round((actualAmount *100.0)/100));
            paymentDTO.setGST(18.0);
            paymentDTO.setFinalAmount(Math.round(booking.getTotalAmount()*100.0)/100.0);
            bookingDTO.setPayment(paymentDTO);
        }


        bookingDTO.setSeats(showSeatDto);

        return bookingDTO;
    }

}

