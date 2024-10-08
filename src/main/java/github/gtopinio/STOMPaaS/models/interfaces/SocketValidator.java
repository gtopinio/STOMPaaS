package github.gtopinio.STOMPaaS.models.interfaces;

import github.gtopinio.STOMPaaS.models.DTOs.SocketDTO;

public interface SocketValidator {
    boolean validate(SocketDTO input);
    boolean isUUID(String value);
    <T extends Enum<T>> boolean isEnumValue(String value, Class<T> enumClass);
}
