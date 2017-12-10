package net.pkhapps.skeleton.application.security;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * A JPA attribute converter for converting between {@link UserRef}s and Strings. Remember to update this class if you
 * change the contents of {@link UserRef}. Try to be brief and only store the information that is absolutely necessary.
 * Details can always be queried from the user database directly.
 */
@Converter(autoApply = true)
public class UserRefConverter implements AttributeConverter<UserRef, String> {

    private static final String SEPARATOR = "|";

    @Override
    public String convertToDatabaseColumn(UserRef attribute) {
        if (attribute == null) {
            return null;
        }
        return String.format("%s%s%s", attribute.getUserId(), SEPARATOR, attribute.getUserName());
    }

    @Override
    public UserRef convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        // Need to escape the separator because it is a special regex char
        String[] parts = dbData.split("\\" + SEPARATOR, 2);
        if (parts.length != 2) {
            throw new IllegalArgumentException("DbData was incorrectly formatted");
        } else {
            return new UserRef(parts[0], parts[1]);
        }
    }
}
