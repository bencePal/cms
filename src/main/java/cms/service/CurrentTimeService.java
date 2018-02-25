package cms.service;

import org.springframework.stereotype.Service;
import java.sql.Timestamp;

@Service
public class CurrentTimeService {

    public Timestamp getCurrentTime() {
        return new Timestamp(System.currentTimeMillis());
    }
}
