package app.controller;

import app.domain.model.Company;
import app.domain.model.MaxSumAdapter;
import app.domain.model.Test;
import app.domain.shared.Constants;
import app.domain.stores.ClientStore;
import app.domain.stores.TestStore;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Calendar;

import static app.domain.shared.Constants.BENCHMARK_ALGORITHM_PATH;
import static app.domain.shared.Constants.BRUTEFORCE_ALGORITHM_PATH;

public class CheckPerformanceController {
    private final Company company;
    private final TestStore tStore;
    private final ClientStore cStore;
    private LocalDateTime[] times;
    private int[] differenceArray;
    private int[] subarray;
    private String option;


    public CheckPerformanceController() {
        this(App.getInstance().getCompany());
    }

    public CheckPerformanceController(Company company) {
        this.company = company;
        this.tStore = company.getTestList();
        this.cStore = company.getClientList();
    }

    public MaxSumAdapter getAdapter(String algorithm) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        MaxSumAdapter adapter;
        if (algorithm.equals("Benchmark Algorithm")) {
            Class<?> oClass = Class.forName(BENCHMARK_ALGORITHM_PATH);
            adapter = (MaxSumAdapter) oClass.newInstance();
        } else {
            Class<?> oClass = Class.forName(BRUTEFORCE_ALGORITHM_PATH);
            adapter = (MaxSumAdapter) oClass.newInstance();
        }

        return adapter;

    }

    public LocalDateTime[] getTimes() {
        return times;
    }

    public int[] getDifferenceArray() {
        return differenceArray;
    }

    public int[] getSubArray(LocalDate beg, LocalDate end, String algorithm) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        MaxSumAdapter adapter = getAdapter(algorithm);

        Test[] tests = new Test[tStore.getTestsInsideDateInterval(beg, end).length];
        int i = 0;
        for (Object o : tStore.getTestsInsideDateInterval(beg, end)) {
            tests[i] = (Test) o;
            i++;
        }
        int numberOfDays = (int) ChronoUnit.DAYS.between(beg, end);
        int numberOfPeriods = numberOfDays * 24;

        times = new LocalDateTime[numberOfPeriods];

        differenceArray = new int[numberOfPeriods];

        int j = 0;


        while (beg.isBefore(end)) {

            LocalDateTime time1 = beg.atTime(8, 0);
            LocalDateTime time3 = beg.atTime(20, 0);

            LocalDateTime time2 = beg.atTime(8, 30);


            while (time2.isBefore(time3)) {
                int created = 0;
                int validated = 0;

                for (Test t : tests) {
                    if (t.getCreatedDate().isAfter(time1) && t.getCreatedDate().isBefore(time2)) {
                        created++;
                    }
                    if (t.getValidatedDate().isAfter(time1) && t.getValidatedDate().isBefore(time2)) {
                        validated++;
                    }

                }

                differenceArray[j] = created - validated;
                times[j] = time1;

                time1 = time1.plusMinutes(30);

                time2 = time1.plusMinutes(30);
                j++;

            }

            beg = beg.plusDays(1);

        }


        subarray = adapter.getMaxSum(differenceArray);

        return subarray;
    }

    public LocalDateTime[] getDates(int[] subarray) {
        int[] begEnd = getBeginEnd(differenceArray, subarray);
        LocalDateTime[] dates = new LocalDateTime[2];

        dates[0] = times[begEnd[0]];
        dates[1] = times[begEnd[begEnd.length - 1]];

        return dates;
    }

    private int[] getBeginEnd(int[] sequence, int[] subSeq) {
        int i;
        int k = 0;
        int[] indexes = new int[subSeq.length];
        if (subSeq.length == 0) {
            return new int[]{0, 0};
        }
        for (i = 0; i < sequence.length; i++) {
            while (sequence[i] == subSeq[k]) {
                indexes[k] = i;
                i++;
                k++;
                if (k == subSeq.length) {
                    return indexes;
                }
                if (sequence[i] != subSeq[k]) {
                    k = 0;
                }
            }
        }
        for (i = 0; i < indexes.length; i++) {
            indexes[i] = 0;
        }

        int[] begEnd = new int[2];
        begEnd[0] = indexes[0];
        begEnd[1] = indexes[indexes.length - 1];
        return begEnd;
    }

    public int numberClients() {
        return cStore.getClientList().size();
    }

    public void getAllTestsInInterval(int inter) {
        this.tStore.getAllTestsInAInterval(inter);
    }

    public void setInformation(String selection) {
        option = selection;
        constructPerformanceReport();
    }

    public int numberWaitingResults() {

        return tStore.getWaitingResult().size();
    }

    public int numberWaitingDiagnosis() {

        return tStore.getWaitingDiagnosis().size();
    }

    public String constructPerformanceReport() {

        StringBuilder sb = new StringBuilder();

        Calendar calendar = Calendar.getInstance();
        int maxDaysThisMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        sb.append("Number of clients: ")
                .append(numberClients())
                .append("\n")
                .append("Number of tests available: ")
                .append(tStore.numberOfTests())
                .append("\n")
                .append("Number of tests waiting for results: ")
                .append(numberWaitingResults())
                .append("\n")
                .append("Number of tests waiting for diagnostic: ")
                .append(numberWaitingDiagnosis())
                .append("\n");

        if (option.equals(Constants.DAY)) {
            sb.append("Tests done today: ")
                    .append(tStore.getAllTestsInAInterval(1))
                    .append("\n");
        } else if (option.equals(Constants.WEEK)) {
            sb.append("Tests done in this week: ")
                    .append(tStore.getAllTestsInAInterval(8))
                    .append("\n");
        } else if (option.equals(Constants.MONTH)) {
            sb.append("Tests done in this month: ")
                    .append(tStore.getAllTestsInAInterval(maxDaysThisMonth))
                    .append("\n");
        } else if (option.equals(Constants.YEAR)) {
            sb.append("Tests done in this year: ")
                    .append(tStore.getAllTestsInAInterval(365))
                    .append("\n");
        }

        sb.append("Max Sum SubArray: ");
        if (subarray.length == 0) {
            sb.append("There is no tests in te selected interval");
        } else {

            sb.append(Arrays.toString(subarray))
                    .append("\n")
                    .append("subArray Interval: ")
                    .append("]").append(getDates(subarray)[0]).append(",").append(getDates(subarray)[1]).append("[")
                    .append("\n");
        }

        return sb.toString();

    }


}





