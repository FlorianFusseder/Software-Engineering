/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Technicals.Repo.BankRepo;
import javax.inject.Inject;

/**
 *
 * @author Florian
 */
public class BankService {

    @Inject
    private BankRepo manager;

    public BankService() {
    }

}
