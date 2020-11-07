
public class find {
    public static void main(String[] args) {
        String str1;
        Stirng str2;
        String max="";
        for (int i=0;i<str1.length();i++){
            for (int j=i;j<str1.length();j++){
                if (str2.indexOf(str1.substring(i,j))!=-1 && str1.substring(i,j).length()>max.length()){
                    max=str1.substring(i,j);
                }
            }
        }
    }
        
}
