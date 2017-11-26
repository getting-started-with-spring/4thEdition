package sample.spring.chapter06.bankapp.service;

import java.util.Calendar;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

@Validated
public interface CustomerRequestService {
	@Future
	Calendar submitRequest(@NotBlank String type, @Size(min=20, max=100) String description, @Past Calendar accountOpeningDate);
}
