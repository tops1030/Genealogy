package util;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;

public class Node extends DefaultMutableTreeNode{
    public String getName(){return name;}
    public boolean getSex(){return sex;}
    public String getBirthplace(){return birthplace;}
    public String getBirthday(){return birthday;}
    public String getDeathday(){return deathday;}
    public int getHeight(){return height;}
    public String getVocation(){return vocation;}
    public Node getSpouse(){return spouse;}

    public Node(String name,boolean sex,String birthplace,String birthday,String deathday,int height,String vocation) {
        this.name = name;
        this.sex = sex;
        this.birthplace = birthplace;
        this.birthday = birthday;
        this.deathday = deathday;
        this.height = height;
        this.vocation = vocation;
        this.spouse=null;
    }

    public void marry(Node spouse){
        this.spouse=spouse;
    }

    private String name;//姓名
    private boolean sex;//性别
    private String birthplace;//出生地点
    private String birthday;//出生日期
    private String deathday;//死亡日期
    private int height;//身高
    private String vocation;//职业
    private Node spouse;//配偶
}
