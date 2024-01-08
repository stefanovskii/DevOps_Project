package mk.finki.ukim.mk.lab.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UserFullnameConverter implements AttributeConverter<UserFullname,String> {

    private static final String SEPARATOR = ", ";

    @Override
    public String convertToDatabaseColumn(UserFullname userFullname) {
        if (userFullname == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        if (userFullname.getSurname() != null && !userFullname.getSurname()
                .isEmpty()) {
            sb.append(userFullname.getSurname());
            sb.append(SEPARATOR);
        }

        if (userFullname.getName() != null
                && !userFullname.getName().isEmpty()) {
            sb.append(userFullname.getName());
        }

        return sb.toString();
    }


    @Override
    public UserFullname convertToEntityAttribute(String dbPersonName) {
        if (dbPersonName == null || dbPersonName.isEmpty()) {
            return null;
        }

        String[] pieces = dbPersonName.split(SEPARATOR);

        if (pieces == null || pieces.length == 0) {
            return null;
        }

        UserFullname personName = new UserFullname();
        String firstPiece = !pieces[0].isEmpty() ? pieces[0] : null;
        if (dbPersonName.contains(SEPARATOR)) {
            personName.setSurname(firstPiece);

            if (pieces.length >= 2 && pieces[1] != null
                    && !pieces[1].isEmpty()) {
                personName.setName(pieces[1]);
            }
        } else {
            personName.setName(firstPiece);
        }

        return personName;
    }

}
