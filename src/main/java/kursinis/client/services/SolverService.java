package kursinis.client.services;

import kursinis.client.SolverType;
import kursinis.client.model.ServiceInput;
import kursinis.client.model.ServiceOutput;
import service.SolverResult;

public interface SolverService {

    ServiceOutput solve(ServiceInput serviceInput);
}
