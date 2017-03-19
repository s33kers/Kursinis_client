package kursinis.client.services;

import kursinis.client.model.ServiceInput;
import kursinis.client.model.ServiceOutput;

public interface SolverService {

    ServiceOutput solve(ServiceInput serviceInput);
}
