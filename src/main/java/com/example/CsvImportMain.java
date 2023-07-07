package com.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.example.domain.Original;

/**
 * csvデータを読み込みオリジナルテーブルに挿入するメイン.
 * 
 * @author yoshimatsushouta
 *
 */
public class CsvImportMain {
	public static void main(String[] args) {

		List<Original> originalList = new ArrayList<>();

		OriginalRepository myBatch = new OriginalRepository();
		String line;// tsvファイルを区切っていない一文の情報
		int index = 0;// tsvファイルの行数を一時保存する

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream("src/main/resources/latest.csv"), Charset.forName("UTF-8")))) {

			// tsvファイルの行数文回す
			while ((line = reader.readLine()) != null) {

				if (index > 0) {// 一行目はカラム名なので飛ばす
					System.out.println(index + "回目-----");
					Original original = new Original();

					String[] data = line.replaceAll("\"", "").split(",");// csvファイルの場合は","
					// tsvファイルのカラムの区切りごとにString型の配列として取得
//					Pattern p = Pattern.compile("^0+([0-9]+.*)");
//					Matcher m = p.matcher(data[0]);
//					if (m.matches()) {
//						System.out.println(m.group(1));
//					}
//					for (String word : data) {
//						System.out.println(word);
//					}
					System.out.println();
					original.setPrefecturesCode(Integer.parseInt(data[0]));
					original.setPrefectures(data[1]);
					original.setPrefecturesKana(data[2]);
					original.setPrefecturesRome(data[3]);
					original.setMunicipalitiesCode(Integer.parseInt(data[4]));
					original.setMunicipalities(data[5]);
					original.setMunicipalitiesKana(data[6]);
					original.setMunicipalitiesRome(data[7]);
					original.setAddress(data[8]);

					try {// 商品説明が無い場合は無視する
						original.setAddressKana(data[9]);

					} catch (Exception e) {
						System.out.println("住所カナはありません");
					}
					try {// 商品説明が無い場合は無視する
						original.setAddressRome(data[10]);

					} catch (Exception e) {
						System.out.println("住所カナはありません");
					}
					try {// 商品説明が無い場合は無視する
						original.setLatitude(Double.parseDouble(data[12]));

					} catch (Exception e) {
						System.out.println("経度はありません");
					}
					try {// 商品説明が無い場合は無視する
						original.setLongitude(Double.parseDouble(data[13]));

					} catch (Exception e) {
						System.out.println("緯度はありません");
					}

					originalList.add(original);// カラム毎に別れた一行ずつOriginal型のリストに挿入
				}
				index++;// 行数を進める

			}
		} catch (IOException e) {
			System.out.println(index + "件目のファイルの読み込みに失敗");
		}
		myBatch.insertToOriginal(originalList);// 取得した情報をinsert
	}

}
