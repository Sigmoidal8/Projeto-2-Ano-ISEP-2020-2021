import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import presentation.WorkflowServer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author Raúl Coelho
 */
@SuppressWarnings("squid:S106")
public final class WorfkflowDaemon{

    private static final Logger LOGGER = LogManager.getLogger(WorfkflowDaemon.class);

    private WorfkflowDaemon() {
    }

    public static void main(final String[] args) throws IOException {
        LOGGER.info("Configuring the daemon");

        String taskExecutorConfigPath = "base.daemon.workflowserver/src/main/resources/taskexecutor.properties";

        Properties appProps = new Properties();
        appProps.load(new FileInputStream(taskExecutorConfigPath));

        List<String> taskExecutors = new ArrayList<>();

        Set<Object> executors = appProps.keySet();
        for(Object o : executors){
            taskExecutors.add((String) appProps.get(o));
        }


        String algorithmConfigPath = "base.daemon.workflowserver/src/main/resources/algorithm.properties";
        Properties algorithmProps = new Properties();
        algorithmProps.load(new FileInputStream(algorithmConfigPath));

        System.setProperty("algorithm", algorithmProps.getProperty("algorithm"));
        
        String assigntasksautomaticConfigPath = "base.daemon.workflowserver/src/main/resources/assigntasksautomatic.properties";
        Properties assigntasksautomaticProps = new Properties();
        assigntasksautomaticProps.load(new FileInputStream(assigntasksautomaticConfigPath));

        System.setProperty("assigntasksautomatic", assigntasksautomaticProps.getProperty("assigntasksautomatic"));

        AuthzRegistry.configure(PersistenceContext.repositories().users(),
                new BasePasswordPolicy(),
                new PlainTextEncoder());

        LOGGER.info("Starting the server socket");
        final WorkflowServer server = new WorkflowServer(taskExecutors);

        LOGGER.info("Exiting the daemon");
        System.exit(0);
    }

}
