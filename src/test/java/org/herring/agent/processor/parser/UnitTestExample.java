package org.herring.agent.processor.parser;

import org.junit.*;
import org.junit.runners.MethodSorters;

/**
 * << Description >>
 * User: hyunje
 * Date: 13. 6. 3.
 * Time: 오후 11:40
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UnitTestExample {

    @Test
    public void parserTest()throws Exception{
        Assert.assertEquals(1,1); //(기대값, 검사할 값), 틀리면 Error
        Assert.assertSame(new Object(), new Object()); //object == object 로 비교
        Assert.assertTrue("message",true); //Condition이 True 이면 통과. 해당 message 출력
        Assert.fail(); //무조건 Exception. 보통 Try ~ Catch에서 사용.
    }

    //매 테스트 메소드 수행 직전에 실행
    @Before
    public void setUp(){

    }

    //매 테스트 메소드 수행 종료시에 실행
    @After
    public void shutDown(){

    }

    //테스트 클래스 생성시 실행
    @BeforeClass
    public static void setUpClass(){

    }

    //테스트 클래스 종료시에 실행
    @AfterClass
    public static void shupDownClass(){

    }
}
