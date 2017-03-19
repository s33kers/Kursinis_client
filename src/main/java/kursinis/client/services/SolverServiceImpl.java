package kursinis.client.services;

import kursinis.client.SolverType;
import kursinis.client.exceptions.InputFormatException;
import kursinis.client.model.ServiceInput;
import kursinis.client.model.ServiceOutput;
import kursinis.client.utils.InputParser;
import org.springframework.stereotype.Component;
import service.ChainSolver;
import service.Input;
import service.ObjectFactory;
import service.SolverResult;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class SolverServiceImpl implements SolverService {

    @Override
    public ServiceOutput solve(ServiceInput serviceInput) {
        ServiceOutput output = new ServiceOutput();
        Input input = null;
        try {
            input = InputParser.parseToInput(serviceInput.getRules(), serviceInput.getFacts(), serviceInput.getGoal());
        } catch (InputFormatException e) {
            output.setError(true);
            e.printStackTrace();
        }


        try {
            URL url = new URL("http://localhost:8080/solver?WSDL");
            QName qname = new QName("http://service/", "ChainSolverImplService");
            Service service = Service.create(url, qname);
            ChainSolver chainSolver = service.getPort(ChainSolver.class);

            SolverResult result = null;
            switch (serviceInput.getType()) {
                case BACKWARD_CHAIN:
                    result = chainSolver.solveBackwardChaining(input);
                    break;
                case FORWARD_CHAIN:
                    result = chainSolver.solveForwardChaining(input);
                    break;
                default:
                    output.setError(true);
                    break;
            }

            output.setResult(result.getResult());
            output.setResultPath(result.getResultPath());
            output.setSolverWorkflow(result.getSolverWorkflow());
            return output;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        output.setError(true);
        return output;
    }

}
