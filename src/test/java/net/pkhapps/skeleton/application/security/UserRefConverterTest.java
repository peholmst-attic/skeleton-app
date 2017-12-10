package net.pkhapps.skeleton.application.security;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for {@link UserRefConverter}.
 */
public class UserRefConverterTest {

    private UserRefConverter converter = new UserRefConverter();

    @Test
    public void convertToDatabaseColumn_nullInput_nullOutput() {
        assertThat(converter.convertToDatabaseColumn(null)).isNull();
    }

    @Test
    public void convertToDatabaseColumn_nonNullInput_validStringOutput() {
        assertThat(converter.convertToDatabaseColumn(new UserRef(1234L, "Joe Cool")))
                .isEqualTo("1234|Joe Cool");
    }

    @Test
    public void convertToEntityAttribute_nullInput_nullOutput() {
        assertThat(converter.convertToEntityAttribute(null)).isNull();
    }

    @Test
    public void convertToEntityAttribute_validStringInput_validUserRefOutput() {
        assertThat(converter.convertToEntityAttribute("1234|Joe Cool"))
                .isEqualTo(new UserRef(1234L, "Joe Cool"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertToEntityAttribute_invalidStringInput_exceptionThrown() {
        converter.convertToEntityAttribute("1234");
    }
}
