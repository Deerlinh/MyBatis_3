import com.github.pagehelper.PageHelper;
import com.kaishengit.entity.Mybatis;
import com.kaishengit.entity.MybatisExample;
import com.kaishengit.mapper.MybatisMapper;
import com.kaishengit.utils.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by 蔡林红 on 2017/10/27.
 */
public class GeneratorTest {
    private SqlSession sqlSession;
    private MybatisMapper mybatisMapper;

    @Before
    public  void init(){
            sqlSession= MyBatisSqlSessionFactory.getSqlSession();
            mybatisMapper=sqlSession.getMapper(MybatisMapper.class);
    }
    @After
    public  void close(){sqlSession.close();}
    @Test
   public void finfByNum(){
        MybatisExample mybatisExample=new MybatisExample();
        mybatisExample.createCriteria().andNameEqualTo("x");
        List<Mybatis> mybatisList=mybatisMapper.selectByExample(mybatisExample);
        for(Mybatis mybatis: mybatisList){
            System.out.println(mybatis.getName());
        }

    }
    @Test
    public void insert(){
        Mybatis mybatis=new Mybatis();
        mybatis.setName("w");
        mybatis.setAge(20);
        mybatis.setClsId(2);
        System.out.println(mybatis);

        mybatisMapper.insertSelective(mybatis);
        sqlSession.commit();

    }
@Test
   public void fingbyId(){
      Mybatis mybatis= mybatisMapper.selectByPrimaryKey(2006);
       System.out.println(mybatis);
   }
   @Test
   public void delete (){
       MybatisExample mybatisExample=new MybatisExample();
       mybatisExample.createCriteria().andNameEqualTo("xixisss");

       mybatisMapper.deleteByExample(mybatisExample);
       sqlSession.commit();

   }

@Test
    public  void desc(){
        PageHelper.offsetPage(1,2);
       MybatisExample mybatisExample= new MybatisExample();
       mybatisExample.createCriteria().andClsIdEqualTo(2);
       mybatisExample.setOrderByClause("id desc");
        List<Mybatis> mybatisList= mybatisMapper.selectByExample(mybatisExample);
        for(Mybatis mybatis :mybatisList){
            System.out.println(mybatis.getName());

        }
}

}
