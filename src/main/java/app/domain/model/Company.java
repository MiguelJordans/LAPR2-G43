package app.domain.model;

import app.controller.App;
import app.controller.SendReportTask;
import app.domain.shared.Serialize;
import app.domain.stores.*;
import auth.AuthFacade;
import auth.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Company implements Serializable {

    private final String designation;
    private final AuthFacade authFacade;

    private final ParameterCategoryStore parameterCategoryList;
    private final ClinicalAnalysisLabStore clinicalAnalysisLabList;
    private final EmployeeStore employeeList;
    private final ClientStore clientList;
    private final TestStore testList;
    private final SampleStore sampleStore;
    private final ParameterStore parameterList;
    private final TestTypeStore testTypeList;
    private final RoleStore roleList;
    private UserSession user;
    private final Data data;
    private boolean createdTask = false;

    /**
     * Constructor of the Company Class, instances a new object of AuthFacade and new empty stores
     *
     * @param designation Designation of Company
     */
    public Company(String designation, String hour, String min, String sec) {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.authFacade = new AuthFacade();
        this.parameterList = new ParameterStore();
        this.roleList = new RoleStore();
        this.testTypeList = new TestTypeStore();
        this.parameterCategoryList = new ParameterCategoryStore();
        this.employeeList = new EmployeeStore();
        this.clinicalAnalysisLabList = new ClinicalAnalysisLabStore();
        this.clientList = new ClientStore();
        this.testList = new TestStore();
        this.sampleStore = new SampleStore();
        this.data = new Data();
        createdTask = scheduleReport(Integer.parseInt(hour), Integer.parseInt(min), Integer.parseInt(sec));

    }

    public Company() {
        Company temp = (Company) readCompany();


        this.designation = Objects.requireNonNull(temp).getDesignation();
        this.authFacade = temp.getAuthFacade();
        this.parameterList = temp.getParameterList();
        this.roleList = temp.getRoleList();
        this.testTypeList = temp.getTestTypeList();
        this.parameterCategoryList = temp.getParameterCategoryList();
        this.employeeList = temp.getEmployeeList();
        this.clinicalAnalysisLabList = temp.getClinicalAnalysisLabList();
        this.clientList = temp.getClientList();
        this.testList = temp.getTestList();
        this.sampleStore = temp.getSampleStore();
        this.data = temp.getData();

    }

    private boolean scheduleReport(int hour, int min, int sec) {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, hour);
        today.set(Calendar.MINUTE, min);
        today.set(Calendar.SECOND, sec);

        Timer timer = new Timer();
        timer.schedule(new SendReportTask(), today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
        return true;
    }

    /**
     * @return designation of the Company
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * @return Object of AuthFacade instantiated by the Company Controller
     */
    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    /**
     * @return the list of Parameters Categories in the System
     */

    public ParameterCategoryStore getParameterCategoryList() {
        return this.parameterCategoryList;
    }

    /**
     * @return the list of Employees in the System
     */

    public EmployeeStore getEmployeeList() {
        return this.employeeList;
    }

    /**
     * @return the list of Parameters in the System
     */
    public ParameterStore getParameterList() {
        return this.parameterList;
    }

    /**
     * @return the list of Types of Tests in the System
     */
    public TestTypeStore getTestTypeList() {
        return this.testTypeList;
    }

    /**
     * @return the list of Roles in the System
     */
    public RoleStore getRoleList() {
        return this.roleList;
    }

    /**
     * @return the list of Clinical Analysis Labs in the System
     */
    public ClinicalAnalysisLabStore getClinicalAnalysisLabList() {
        return this.clinicalAnalysisLabList;
    }

    /**
     * @return the list of Clients in the System
     */
    public ClientStore getClientList() {
        return this.clientList;
    }

    public List<Client> getClientArrayList() {
        return getClientList().getClientList();
    }

    /**
     * @return the list of Tests in the System
     */
    public TestStore getTestList() {
        return this.testList;
    }

    /**
     * @return the list of Samples in the System
     */
    public SampleStore getSampleStore() {
        return sampleStore;
    }


    public String getUserID() {
        return user.getUserId().toString();
    }


    public boolean saveCompany() {
        Properties props = App.getProperties();
        return Serialize.writeObject(props.getProperty("serialize.path"), this);
    }

    private Object readCompany() {
        Properties props = App.getProperties();

        try {
            return Serialize.readFile(props.getProperty("serialize.path"));
        } catch (IOException e) {
            return null;
        }
    }

    public Data getData() {
        return data;
    }

    public boolean isCreatedTask() {
        return createdTask;
    }


}