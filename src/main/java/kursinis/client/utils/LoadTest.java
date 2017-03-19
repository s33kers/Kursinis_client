package kursinis.client.utils;

import kursinis.client.SolverType;
import kursinis.client.model.ServiceInput;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tadas.
 */
public class LoadTest {

    private static Map<Long, ServiceInput> backward = new HashMap<>();
    private static Map<Long, ServiceInput> forward = new HashMap<>();

    static {
        backward.put(1L, new ServiceInput("Z F B\nF C D\nD A", "A B C", "Z", SolverType.BACKWARD_CHAIN));
        backward.put(2L, new ServiceInput("M K\nZ M N\nZ F B\nF C D\nD A", "A B C", "Z", SolverType.BACKWARD_CHAIN));

        forward.put(1L, new ServiceInput("Z F B\nF C D\nD A", "A B C", "Z", SolverType.FORWARD_CHAIN));
        forward.put(2L, new ServiceInput("Z C\nB A", "A", "Z", SolverType.FORWARD_CHAIN));
    }

    public static ServiceInput loadTest(Long id, String type) {
        ServiceInput serviceInput = null;
        switch (type) {
            case "backward":
                if (backward.containsKey(id)) {
                    serviceInput = backward.get(id);
                }
                break;
            case "forward":
                if (forward.containsKey(id)) {
                    serviceInput = forward.get(id);
                }
                break;
        }
        if (serviceInput == null) {
            serviceInput = new ServiceInput();
        }
        return serviceInput;
    }
}
