package com.cfs.bookMyShow.service;

import com.cfs.bookMyShow.dto.*;
import com.cfs.bookMyShow.exception.SeatNotAvailableException;
import com.cfs.bookMyShow.exception.ShowNotFoundException;
import com.cfs.bookMyShow.exception.UserNotFoundException;
import com.cfs.bookMyShow.model.*;
import com.cfs.bookMyShow.model.type.BookingStatus;
import com.cfs.bookMyShow.model.type.PaymentStatus;
import com.cfs.bookMyShow.model.type.SeatStatus;
import com.cfs.bookMyShow.repository.BookingRepository;
import com.cfs.bookMyShow.repository.ShowRepository;
import com.cfs.bookMyShow.repository.ShowSeatRepository;
import com.cfs.bookMyShow.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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

    @Value("${app.gst.rate}")
    private   Double GST;



    @Transactional
    public  BookingDTO createBooking(RequestBookingDTO dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(()->  new  UserNotFoundException("USER NOT FOUND"));

        Show show =  showRepository.findById(dto.getShowId())
                .orElseThrow(()-> new ShowNotFoundException("SHOW NOT FOUND"));

//        List<ShowSeat> showSeats =  showSeatRepository.findAllById(dto.getSeatIds());
        List<ShowSeat> showSeats = showSeatRepository.findSeatsForUpdate(dto.getShowId(), dto.getSeatIds());


//      List<Seat> seats =   showSeats.stream()
//                .filter(seat -> !"AVAILABLE".equals(seat.getBookingStatus()))
//                .findAny()
//                .ifPresent(seat -> {
//                    throw new SeatNotAvailableException(
//                            "Seat " + seat.getSeat().getId() + " is not available"
//                    );
//                });

        showSeats.forEach(
                seat-> {
                    if (seat.getStatus() != SeatStatus.AVAILABLE)
                        throw new SeatNotAvailableException(
                                "Seat " + seat.getSeat().getId() + " is not available"
                        );
                    seat.setStatus(SeatStatus.LOCKED);
                }
        );
        showSeatRepository.saveAll(showSeats);
        Double amount =  showSeats.stream()
                .mapToDouble(
                        ShowSeat::getPrice
                ).sum();


        Double finalAmount = amount * (1 + GST / 100);


        Payment payment = new Payment();
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setPaymentTime(LocalDateTime.now());
        payment.setPaymentMethod("Gpay");
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setAmount(amount);
        payment.setGST(GST);
        payment.setTotalAmount(finalAmount);

        Booking  booking = new Booking();

        booking.setBookingTime(LocalDateTime.now());
        booking.setStatus(BookingStatus.CONFIRM);
        booking.setShow(show);
        booking.setPayment(payment);
        booking.setShowSeats(showSeats);
        booking.setUser(user);
        booking.setTotalAmount(finalAmount);
        booking.setBookingNumber(UUID.randomUUID());

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

    private BookingDTO mapToDto(Booking booking , List<ShowSeat> showSeats ) {
        UserDTO userDTO =  new UserDTO();
        userDTO.setId(booking.getUser().getId());
        userDTO.setName(booking.getUser().getName());
        userDTO.setEmail(booking.getUser().getEmail());

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

        BookingDTO  bookingDTO =  new BookingDTO();
        bookingDTO.setBookingNumber(booking.getBookingNumber());
        bookingDTO.setBookingTime(booking.getBookingTime());
        booking.setStatus(booking.getStatus());
        bookingDTO.setTotalAmount(booking.getTotalAmount());
        bookingDTO.setUser(userDTO);
        bookingDTO.setShow(showDTO);

        TheaterDTO theaterDto=new TheaterDTO();
        theaterDto.setId(bookingDTO.getShow().getScreenId());
        Theater  theater = booking.getShow().getScreen().getTheater();
        theaterDto.setName(theater.getName());
        theaterDto.setAddress(theater.getAddress());
        theaterDto.setCityName(theater.getCity());
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
            paymentDTO.setAmount(booking.getTotalAmount());
            bookingDTO.setPayment(paymentDTO);
        }


        bookingDTO.setSeats(showSeatDto);

        return bookingDTO;
    }


}
