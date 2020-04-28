package it.unina.p2.proxyskeleton.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ServizioReale extends Skeleton {

	Lock lock;
	
	public ServizioReale(){
		lock = new ReentrantLock();
	}
	
	@Override
	public void salvaSuFile(int id) {
		lock.lock();
		try {
			String filePath = new File("").getAbsolutePath();
			FileWriter fw = new FileWriter(filePath+"/src/it/unina/p2/proxyskeleton/file/myfile.txt",true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(String.valueOf(id));
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			lock.unlock();
		}

	}

	@Override
	public int filePop() {
		FileReader fr;
		lock.lock();
		try {
			String filePath = new File("").getAbsolutePath();
			fr = new FileReader(filePath+"/src/it/unina/p2/proxyskeleton/file/myfile.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			ArrayList<Integer> ints = new ArrayList<Integer>();
			while((line = br.readLine()) != null){
				ints.add(Integer.decode(line));
			}
			br.close();
			return ints.get(ints.size() - 1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			lock.unlock();
		}
		return 0;
	}

}
