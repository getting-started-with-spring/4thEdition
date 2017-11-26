package sample.spring.chapter03.bankapp.controller;

import sample.spring.chapter03.bankapp.domain.Request;

public interface UserRequestController {
	void submitRequest(Request request);
}
