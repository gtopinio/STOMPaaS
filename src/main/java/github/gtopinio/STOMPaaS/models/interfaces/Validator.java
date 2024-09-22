package github.gtopinio.STOMPaaS.models.interfaces;

import github.gtopinio.STOMPaaS.models.DTOs.SocketDTO;

public interface Validator {
    boolean validate(SocketDTO input);
}
