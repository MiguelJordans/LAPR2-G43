package app.domain.stores;


import app.domain.model.Sample;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class SampleStoreTest {

    @Test
    public void createSampleTest() throws ClassNotFoundException, InstantiationException, BarcodeException, IllegalAccessException {
        //Arrange + act
        SampleStore store = new SampleStore();

        //Assert
        Assert.assertTrue(store.createSample("BL000"));
    }

    @Test
    public void saveSampleTest() throws ClassNotFoundException, InstantiationException, BarcodeException, IllegalAccessException, IOException, OutputException {
        //Arrange + act
        SampleStore store = new SampleStore();
        store.createSample("BL000");

        //Assert
        Assert.assertTrue(store.saveSample());
    }

    @Test
    public void validateSampleTest() throws ClassNotFoundException, InstantiationException, BarcodeException, IllegalAccessException {
        //Arrange + act
        SampleStore store = new SampleStore();
        store.createSample("BL000");

        //Assert
        Assert.assertTrue(store.validateSample());
    }

    @Test
    public void createSampleIDTest() {
        //Arrange + act

        SampleStore store = new SampleStore();
        String s = "00000000001";
        String a = store.createSampleID();
        //Assert
        Assert.assertEquals(s,a);
    }

    @Test
    public void getSampleTest() throws ClassNotFoundException, InstantiationException, BarcodeException, IllegalAccessException {
        //Arrange + act

        SampleStore store = new SampleStore();
        String a = "Sample: testID=BL000, barcode=00000000001";
        store.createSample("BL000");
        String b = store.getSample();

        Assert.assertEquals(a,b);

    }





}
