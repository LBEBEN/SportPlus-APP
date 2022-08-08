package pl.lukasbeben.bucklet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.lukasbeben.trainer.Trainer;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BuckletService {

    private final BuckletDao buckletDao;

    public List<Bucklet> showAll(){
        return buckletDao.showAll();
    }
    public Bucklet findById (int id){
        return buckletDao.findById(id);
    }

    public void addBucklet(Bucklet bucklet){buckletDao.addBucklet(bucklet);}

    public void editBucklet(Bucklet bucklet){
        buckletDao.edit(bucklet);
    }

    public void deleteBucklet (Bucklet bucklet) {buckletDao.delete(bucklet);
    }

}
