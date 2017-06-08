package kursinis.client.services;

import kursinis.client.exceptions.InputFormatException;
import kursinis.client.model.ServiceInput;
import kursinis.client.model.ServiceOutput;
import kursinis.client.utils.InputParser;
import org.springframework.stereotype.Component;
import service.ChainSolver;
import service.ChainSolverImplService;
import service.Input;
import service.SolverResult;
import service.Unsolvable_Exception;

import javax.xml.ws.Service;

@Component
public class SolverServiceImpl implements SolverService {

    @Override
    public ServiceOutput solve(ServiceInput serviceInput) {
        ServiceOutput output = new ServiceOutput();
        Input input;
        try {
            input = InputParser.parseToInput(serviceInput.getRules(), serviceInput.getFacts(), serviceInput.getGoal());
        } catch (InputFormatException e) {
            output.setError(true);
            output.setResult(e.getMessage());
            return output;
        }

        Service service = new ChainSolverImplService();
        ChainSolver chainSolver = service.getPort(ChainSolver.class);
        SolverResult solverResult = null;
        switch (serviceInput.getType()) {
            case BACKWARD_CHAIN:
                try {
                    solverResult = chainSolver.solveBackwardChaining(input);
                } catch (Unsolvable_Exception e) {
                    output.setError(true);
                    output.setResult(e.getFaultInfo().getMessage());
                    return output;
                }
                break;
            case FORWARD_CHAIN:
                solverResult = chainSolver.solveForwardChaining(input);
                break;
            default:
                output.setError(true);
                break;
        }

        output.setResult(solverResult.getResult());
        output.setResultPath(solverResult.getResultPath());
        output.setSolverWorkflow(solverResult.getSolverWorkflow().replaceAll("\\n", "<br>"));
        return output;
    }

}
