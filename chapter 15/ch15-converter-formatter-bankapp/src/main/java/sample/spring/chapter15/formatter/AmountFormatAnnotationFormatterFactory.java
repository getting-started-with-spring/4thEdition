package sample.spring.chapter15.formatter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

public class AmountFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<AmountFormat> {

	public Set<Class<?>> getFieldTypes() {
		Set<Class<?>> fieldTypes = new HashSet<Class<?>>(1, 1);
		fieldTypes.add(Long.class);
		return fieldTypes;
	}

	public Parser<?> getParser(AmountFormat annotation, Class<?> fieldType) {
		return new AmountFormatter();
	}

	public Printer<?> getPrinter(AmountFormat annotation, Class<?> fieldType) {
		return new AmountFormatter();
	}
}
