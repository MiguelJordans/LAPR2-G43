package app.controller;

import app.domain.mappers.TestParameterListMapper;
import app.domain.mappers.dto.TestParameterDTO;
import app.domain.model.Company;
import app.domain.model.Sample;
import app.domain.model.Test;
import app.domain.model.TestParameter;
import app.domain.stores.SampleStore;
import app.domain.stores.TestStore;

import java.util.List;

public class RecordResultsController {
    private Company company;
    private TestStore tStore;
    private List<TestParameter> pList;
    private Test t;
    private SampleStore sampleStore;

    public RecordResultsController() {
        this(App.getInstance().getCompany());
    }

    public RecordResultsController(Company company) {
        this.company = company;
        this.tStore = company.getTestList();
        this.sampleStore = company.getSampleStore();

    }

    public List<TestParameterDTO> getTestParameterList(String sampleId) {
        Sample sample = sampleStore.getSample(sampleId);
        this.t = tStore.getTestByCode(sample.getTestID());
        if (this.t == null) {
            throw new IllegalStateException("Sample does not exists");
        } else {
            pList = t.getTestParam();
            TestParameterListMapper testParameterListMapper = new TestParameterListMapper();
            return testParameterListMapper.toDTO(pList);
        }
    }

    public boolean addTestResult(String parameterCode, double result) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (t.addTestResult(parameterCode, result)) {
            return true;
        }
        return false;
    }

    public String getResults() {

        return this.t.getResults();

    }
    public void saveTest(){
        t.changeState("SAMPLE_ANALYSED");
    }
}
