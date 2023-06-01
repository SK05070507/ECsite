package katachi.spring.exercise.config;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Configuration
public class JavaConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public Errors errors() {
		return new Errors() {

			@Override
			public void setNestedPath(String nestedPath) {
			}

			@Override
			public void rejectValue(String field, String errorCode, Object[] errorArgs, String defaultMessage) {
			}

			@Override
			public void rejectValue(String field, String errorCode, String defaultMessage) {
			}

			@Override
			public void rejectValue(String field, String errorCode) {
			}

			@Override
			public void reject(String errorCode, Object[] errorArgs, String defaultMessage) {
			}

			@Override
			public void reject(String errorCode, String defaultMessage) {
			}

			@Override
			public void reject(String errorCode) {
			}

			@Override
			public void pushNestedPath(String subPath) {
			}

			@Override
			public void popNestedPath() throws IllegalStateException {
			}

			@Override
			public boolean hasGlobalErrors() {
				return false;
			}

			@Override
			public boolean hasFieldErrors(String field) {
				return false;
			}

			@Override
			public boolean hasFieldErrors() {
				return false;
			}

			@Override
			public boolean hasErrors() {
				return false;
			}

			@Override
			public String getObjectName() {
				return null;
			}

			@Override
			public String getNestedPath() {
				return null;
			}

			@Override
			public List<ObjectError> getGlobalErrors() {
				return null;
			}

			@Override
			public int getGlobalErrorCount() {
				return 0;
			}

			@Override
			public ObjectError getGlobalError() {
				return null;
			}

			@Override
			public Object getFieldValue(String field) {
				return null;
			}

			@Override
			public Class<?> getFieldType(String field) {
				return null;
			}

			@Override
			public List<FieldError> getFieldErrors(String field) {
				return null;
			}

			@Override
			public List<FieldError> getFieldErrors() {
				return null;
			}

			@Override
			public int getFieldErrorCount(String field) {
				return 0;
			}

			@Override
			public int getFieldErrorCount() {
				return 0;
			}

			@Override
			public FieldError getFieldError(String field) {
				return null;
			}

			@Override
			public FieldError getFieldError() {
				return null;
			}

			@Override
			public int getErrorCount() {
				return 0;
			}

			@Override
			public List<ObjectError> getAllErrors() {
				return null;
			}

			@Override
			public void addAllErrors(Errors errors) {
			}
		};
	}
}
