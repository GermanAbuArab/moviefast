package um.edu.tic1.client.rest;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import um.edu.tic1.client.models.Cine;

@Service
public class Rest {


    public void saveCine(Cine cine) {
        RestTemplate restTemplate=new RestTemplate();

    }
}
