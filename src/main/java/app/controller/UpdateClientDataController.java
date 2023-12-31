package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.stores.ClientStore;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateClientDataController {

    private final Company company = App.getInstance().getCompany();
    private final Client client;
    private final ClientStore clientStore = company.getClientList();

    public UpdateClientDataController() {
        String email = App.getInstance().getCurrentUserSession().getUserId().toString();
        this.client = company.getClientList().getClient();
    }

    public String getClient(){
        return client.toString();
    }

    public void changeData(int i, String data) throws ParseException {
        switch (i) {
            case 1:
                this.client.setPhoneNumber(data);
                break;

            case 2:
                this.client.setCc(data);
                break;

            case 3:
                this.client.setNhs(data);
                break;

            case 4:
                this.client.setTinNumber(data);
                break;

            case 5:
                Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(data);
                this.client.setBirthDate(date1);
                break;

            case 6:
                this.client.setSex(data);
                break;


            case 7:
                this.client.setEmail(data);
                break;

            case 8:
                this.client.setName(data);
                break;

            default:
                break;
        }

    }

    public void updateClientData(String cc, String nhs, Date birthDate, char sex, String tinNumber, String phoneNumber, String email, String name) {
        this.client.setCc(cc);
        this.client.setNhs(nhs);
        this.client.setBirthDate(birthDate);
        this.client.setSex(String.valueOf(sex));
        this.client.setTinNumber(tinNumber);
        this.client.setPhoneNumber(phoneNumber);
        this.client.setEmail(email);
        this.client.setName(name);

        clientStore.setClient(this.client);
        clientStore.saveClient();
        company.saveCompany();
    }
}

