import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import javafx.stage.FileChooser;

public class chooseExam {
	
	static boolean isLoadFile = false;
	static String fileName = "";
	static String filePath = "";
	static FileChooser fileChooser = new FileChooser();
	static ArrayList<String> musicList = new ArrayList<>();
	static ArrayList<String> musicPath = new ArrayList<>();
	static File mFile = new File("music.txt");
	public chooseExam() {
		loadList();
	}
	public static void main(String[] args) {
		JFileChooser chooser = new JFileChooser();
		
		int ret = chooser.showOpenDialog(null);
		
		if(ret!=JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "경로를 선택하지 않았음!", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		String filepath = chooser.getSelectedFile().getPath();
		String fileName = chooser.getSelectedFile().getName();
		System.out.println(filepath);
		System.out.println(fileName);
		musicList.add(fileName);
		musicPath.add(filepath);
		saveList();
		
		
	}
	
	public void loadList() {
		if(mFile.exists()) {
			BufferedReader br = null;
			try {
				FileReader fr = new FileReader(mFile);
				 br = new BufferedReader(fr);
				String s = "";
				while((s=br.readLine())!=null){
					String[] mFileContents = s.split(",,,,");
					musicList.add(mFileContents[0]);
					musicPath.add(mFileContents[1]);
				}
				isLoadFile = true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(br!=null) {
					try {
						br.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	
	public static void saveList() {
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		try {
			fw = new FileWriter(mFile);
			bw = new BufferedWriter(fw);
			for(int i = 0; i < musicList.size(); i++) {
				System.out.println(musicList.get(i));
				String s = musicList.get(i)+",,,,"+musicPath.get(i)+"\n";
				bw.write(s);
			}
			bw.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

}
