import Sem2.ClassWork.Paket.Square;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by admin on 26.05.2017.
 */
public class SquareTest {
    @Test
    public void test0(){
        double[] x = Square.solve(1,0,1);
        double[] expect = {};
        Assert.assertArrayEquals(expect,x,1e-8);
    }
    @Test
    public void test1(){
        double[] x = Square.solve(1,-2,1);
        double[] expect = {1};
        Assert.assertArrayEquals(expect,x,1e-8);
    }
    @Test
    public void test2(){
        double[] x = Square.solve(1,-5,6);
        double[] expect = {3,2};
        Assert.assertArrayEquals(expect,x,1e-8);
    }
}
