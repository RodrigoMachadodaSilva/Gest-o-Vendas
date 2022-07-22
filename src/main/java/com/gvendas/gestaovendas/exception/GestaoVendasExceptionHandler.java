package com.gvendas.gestaovendas.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GestaoVendasExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String CONSTANT_VALIDATION_NOT_BLANK = "NotBlank";
	private static final String CONSTANT_VALIDATION_NOT_NULL = "NotNull";
	private static final String CONSTANT_VALIDATION_LENGTH = "length";
	private static final String CONSTANT_VALIDATION_PATTERN = "Pattern";

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Error> erros = gerarListadeErros(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}

	private List<Error> gerarListadeErros(BindingResult bindingResult) {
		List<Error> erros = new ArrayList<Error>();
		bindingResult.getFieldErrors().forEach(fieldError -> {
			String msgUsuario = tratarMensagemDeErroParaUsuario(fieldError);
			String msgDesenvolvedor = fieldError.toString();
			erros.add(new Error(msgUsuario, msgDesenvolvedor));

		});
		return erros;

	}

	private String tratarMensagemDeErroParaUsuario(FieldError fielderror) {
		if (fielderror.getCode().equals(CONSTANT_VALIDATION_NOT_BLANK)) {
			return fielderror.getDefaultMessage().concat(" é obrigatório");
		}
		if (fielderror.getCode().equals(CONSTANT_VALIDATION_NOT_NULL)) {
			return fielderror.getDefaultMessage().concat(" é obrigatório");
		}
		if (fielderror.getCode().equals(CONSTANT_VALIDATION_PATTERN)) {
			return fielderror.getDefaultMessage().concat(" é obrigatório");
		}
	    if (fielderror.getCode().equals(CONSTANT_VALIDATION_LENGTH)) {
		 return fielderror.getDefaultMessage().concat(String.format(" deve ter entre %s e %s caracteres",
				 fielderror.getArguments()[2], fielderror.getArguments()[1]));
		}
		return fielderror.toString();

	}

	// Método para tratar a exception uma categoria não encontrada
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
			WebRequest request) {
		String msgUsuario = "Recurso não encontrado.";
		String msgDesenvolvedor = ex.toString();
		List<Error> erros = Arrays.asList(new Error(msgUsuario, msgDesenvolvedor));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(RegraNegocioException.class)
	public ResponseEntity<Object> handleRegraNegocioException(RegraNegocioException ex, WebRequest request) {
		String msgUsuario = ex.getMessage();
		String msgDesenvolvedor = ex.getMessage();
		List<Error> erros = Arrays.asList(new Error(msgUsuario, msgDesenvolvedor));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
			WebRequest request) {
		String msgUsuario = "Recurso não encontrado";
		String msgDesenvolvedor = ex.toString();
		List<Error> erros = Arrays.asList(new Error(msgUsuario, msgDesenvolvedor));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

	}

}
