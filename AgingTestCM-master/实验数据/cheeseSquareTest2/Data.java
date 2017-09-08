import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class Data {
	List<String> listDalvikHeapSize = new ArrayList<String>();
	List<String> listUsedDalvikHeap = new ArrayList<String>();
	List<String> listFreeDalvikHeap = new ArrayList<String>();
	List<String> listNativeHeapSize = new ArrayList<String>();
	List<String> listDalvikPssTotal = new ArrayList<String>();
	List<String> listNativePssTotal = new ArrayList<String>();
	List<String> listUtilizationDalvik = new ArrayList<String>();
	String filePath;
	Data(String str){
		this.filePath = str;
	}
	void DataProcess(){
		try {
            String encoding="GBK";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    Manage(lineTxt);
                }
                read.close();
    }else{
        System.out.println("找不到指定的文件");
    }
    } catch (Exception e) {
        System.out.println("读取文件内容出错");
        e.printStackTrace();
    }
	}
	void Manage(String str){
		Pattern pattern = Pattern.compile("(Dalvik Heap\\s+(\\d+)\\s+\\d+\\s+\\d+\\s+\\d+\\s+(\\d+)\\s+(\\d+)\\s+(\\d+))");		 
		Pattern pattern2 = Pattern.compile("(Native Heap\\s+(\\d+)\\s+\\d+\\s+\\d+\\s+\\d+\\s+(\\d+)\\s+(\\d+)\\s+(\\d+))");
		Matcher matcher = pattern.matcher(str);
		Matcher matcher2 = pattern2.matcher(str);
		if(matcher.find()){
	//		System.out.println("true");
			listDalvikPssTotal.add(matcher.group(2));
			listDalvikHeapSize.add(matcher.group(3));
			listUsedDalvikHeap.add(matcher.group(4));
			listFreeDalvikHeap.add(matcher.group(5));
		}
		else if(matcher2.find()){
			listNativePssTotal.add(matcher2.group(2));
			listNativeHeapSize.add(matcher2.group(3));
		}
		
		
		
	}
	
	
	
	void OutPutList(){
		for(int i = 0; i < listNativePssTotal.size()-1; i++){
//			System.out.println(listNativePssTotal.get(i));
		}
		for(int i = 0; i < listDalvikPssTotal.size()-1; i++){
//			System.out.println(listDalvikPssTotal.get(i));
		}
		
	}
	void OutPutFile(String str) throws IOException{
		for(int i = 0 ; i < listDalvikHeapSize.size() - 1; i++){
			listUtilizationDalvik.add(String.valueOf(Double.parseDouble(listUsedDalvikHeap.get(i))/Double.parseDouble(listDalvikHeapSize.get(i))));
	//		System.out.println(listUtilizationDalvik.get(i));
			}
		File file = new File(str);
		if(!file.exists()){
			file.createNewFile();
		}

		BufferedWriter bw = new BufferedWriter(new FileWriter(str));
		bw.write("DalvikHeapPss");
		bw.write("\t");
		bw.write("DalvikHeapSize");
		bw.write("\t");
		bw.write("UsedDalvikHeap");
		bw.write("\t");
		bw.write("FreeDalvikHeap");
		bw.write("\t");
		bw.write("NativePssTotal");
		bw.write("\t");
		bw.write("NativeHeapSize");
		bw.write("\t");
		bw.write("UtilizationDalvik");
		bw.newLine();
		for(int i = 0; i < listDalvikHeapSize.size() - 1; i++){
			bw.write(listDalvikPssTotal.get(i));
			bw.write("\t");
			bw.write(listDalvikHeapSize.get(i));
			bw.write("\t");
			bw.write(listUsedDalvikHeap.get(i));
			bw.write("\t");
			bw.write(listFreeDalvikHeap.get(i));
			bw.write("\t");
			bw.write(listNativePssTotal.get(i));
			bw.write("\t");
			bw.write(listNativeHeapSize.get(i));
			bw.write("\t");
			bw.write(listUtilizationDalvik.get(i));
			bw.newLine();
		}
		bw.close();
	}
	
	
	
	Double StringtoDouble(String str){
		return Double.parseDouble(str);
	}
	
	List<Double> listStringtoDouble(List<String> list){
		List<Double> listDouble = new ArrayList<Double>();
		for(int i = 0; i < list.size() -1;i++){
			listDouble.add(StringtoDouble(list.get(i)));
		}
		return listDouble;
	}
	Double slopeEstimate(List<String> list){
		List<Double> listDouble = new ArrayList<Double>();
		listDouble = listStringtoDouble(list);
		List<Double> listResult = new ArrayList<Double>();
		for(int i = 0; i < listDouble.size()-2;i++){
			for(int j = i; j < listDouble.size()-1;j++){
				listResult.add((listDouble.get(j) - listDouble.get(i))/(j - i));
			}
		}
		Collections.sort(listResult);
	/*	for(int i = 0; i < listResult.size() -1 ;i++){
			System.out.println(listResult.get(i));
		}
		*/
		if(listResult.size()  == 0){
			return 0.0;
		}
		return listResult.size()%2 == 1 ? listResult.get((listResult.size()+1)/2):(listResult.get(listResult.size()/2)+listResult.get(listResult.size()/2+1))/2;
	}
	void TrendTestResult(String str) throws IOException{
		File file = new File(str);
		if(!file.exists()){
			file.createNewFile();
		}
		BufferedWriter bw2 = new BufferedWriter(new FileWriter(str));
		bw2.write(TrendTest(listDalvikPssTotal,"DalvikPssTotal:"));
		bw2.write("\t");
		bw2.write(slopeEstimate(listDalvikPssTotal).toString());
		bw2.newLine();
		bw2.write(TrendTest(listDalvikHeapSize,"DalvikHeapSize:"));
		bw2.write("\t");
		bw2.write(slopeEstimate(listDalvikHeapSize).toString());
		bw2.newLine();
		bw2.write(TrendTest(listUsedDalvikHeap,"UsedDalvikHeap:"));
		bw2.write("\t");
		bw2.write(slopeEstimate(listUsedDalvikHeap).toString());
		bw2.newLine();
		bw2.write(TrendTest(listFreeDalvikHeap,"FreeDalvikHeap:"));
		bw2.write("\t");
		bw2.write(slopeEstimate(listFreeDalvikHeap).toString());
		bw2.newLine();
		bw2.write(TrendTest(listNativePssTotal,"NativePssTotal:"));
		bw2.write("\t");
		bw2.write(slopeEstimate(listNativePssTotal).toString());
		bw2.newLine();
		bw2.write(TrendTest(listNativeHeapSize,"NativeHeapSize:"));
		bw2.write("\t");
		bw2.write(slopeEstimate(listNativeHeapSize).toString());
		bw2.newLine();
		bw2.write(TrendTest(listUtilizationDalvik,"UtilizationDalvik:"));
		bw2.write("\t");
		bw2.write(slopeEstimate(listUtilizationDalvik).toString());
		bw2.close();

	}
	String TrendTest(List<String> list,String str) throws IOException{
		int signal = 0;
		double result = 0.0;
		for(int i = 1 ; i < list.size()-1;i++){
			for(int j = 0; j < i;j++){
				if(Double.parseDouble(list.get(i)) > Double.parseDouble(list.get(j))){
					signal = signal + 1;
				}else if(Double.parseDouble(list.get(i)) < Double.parseDouble(list.get(j))){
					signal = signal -1;
				}else{
					
				}
			}
		}
		if(signal > 0){
			result = (signal -1)/(Math.sqrt(list.size())*Math.sqrt(list.size()-1)*Math.sqrt(2*list.size()+5)/Math.sqrt(18));
		}else if(signal < 0){
			result = (signal -1)/(Math.sqrt(list.size())*Math.sqrt(list.size()-1)*Math.sqrt(2*list.size()+5)/Math.sqrt(18));
		}else{
			result = 0.0;
		}
		
		if(Math.abs(result) > 1.28 && Math.abs(result) < 1.64){
			return  result >0 ? "增长趋势通过90%检验"+str: "减小趋势通过90%检验"+str;
		}else if(Math.abs(result) > 1.64 && Math.abs(result) < 2.32){
			return  result >0 ? "增长趋势通过95%检验"+str: "减小趋势通过95%检验"+str;			
		}else if(Math.abs(result) > 2.32){
			return  result >0 ? "增长趋势通过99%检验"+str: "减小趋势通过99%检验"+str;			
		}else{
			return "没有通过检验"+str;
		}
			
	}
	
	
	
	
	public static void main(String[] args) throws IOException{
		String path  = ".\\";
		File file = new File(path);
		File[] tempList = file.listFiles();
		System.out.println("该目录下对象个数:" + tempList.length);
		String match = args[0];
		String name = args[1];
			
		if(match == "two"){
			System.out.println("111");
			Data data = new Data(path+name);
		    data.DataProcess();
			data.OutPutFile(name + "data.txt");
			data.TrendTestResult(name + "trendTest.txt");  //趋势检验
			
		
		}
		else{
			for(int i = 0; i < tempList.length; i++){
				if(tempList[i].isFile()){
					System.out.println("文件:" + tempList[i]);
				    Data data = new Data(tempList[i].toString());
				    data.DataProcess();
					data.OutPutFile(tempList[i].toString() + "data.txt");
					data.TrendTestResult(tempList[i].toString() + "trendTest.txt");  //趋势检验
				}
		}
		}
		
		
	//	Data data = new Data("D:\\3H3Game\\Data\\2016-11-16\\getAppData\\appservice");
		//data.DataProcess();		
	//	data.OutPutFile("D:\\3H3Game\\Data\\2016-11-16\\getAppData\\appservice2.txt");
	//	data.TrendTestResult("D:\\3H3Game\\Data\\2016-11-16\\getAppData\\appservice2trendTest.txt");  //趋势检验
	}
}
