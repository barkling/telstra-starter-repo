package au.com.telstra.simcardactivator.jpa2h2;

import org.springframework.data.repository.CrudRepository;


public interface CustomerRespository extends CrudRepository<ActivationRecord,Long> {
    ActivationRecord findByCustomerId(long simCardId);

    ActivationRecord findByIccid(String iccid);
}
