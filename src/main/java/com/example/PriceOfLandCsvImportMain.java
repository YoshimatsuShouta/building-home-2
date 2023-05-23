package com.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 地価csvから情報を取り出しテーブルに挿入するメイン.
 * 
 * @author yoshimatsushouta
 *
 */
public class PriceOfLandCsvImportMain {
	public static void main(String[] args) {

		List<PriceOfLand> priceOfLandList = new ArrayList<>();

		PriceOfLandRepository myBatch = new PriceOfLandRepository();
		String line;// tsvファイルを区切っていない一文の情報
		int index = 0;// tsvファイルの行数を一時保存する
		// "富士見１丁目"と"８番６"に分割する正規表現パターン

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream("src/main/resources/R3kouji_chiten_data.csv"), Charset.forName("Shift-JIS")))) {

			// tsvファイルの行数文回す
			while ((line = reader.readLine()) != null) {

				if (index > 0) {// 一行目はカラム名なので飛ばす
					System.out.println(index + "回目-----");
					PriceOfLand priceOfLand = new PriceOfLand();

					String[] data = line.replaceAll("\"", "").split(",");// csvファイルの場合は","
					// tsvファイルのカラムの区切りごとにString型の配列として取得

					priceOfLand.setMunicipalitiesCode(Integer.parseInt(data[0]));
					if (!startsWithDigit(data[10])) {
						priceOfLand.setAddressNum(data[10]);
					} else {
						priceOfLand.setAddressNum(data[7].replaceAll("　", ""));
					}

					priceOfLand.setCurrentPrice(Integer.parseInt(data[11]));
					try {
						priceOfLand.setPreviosPrice(Integer.parseInt(data[12]));
					} catch (Exception e) {
						System.out.println("前年度の地価はありません");
					}
					try {
						priceOfLand.setChangePreviosPrice(Double.parseDouble(data[13]));
					} catch (Exception e) {
						System.out.println("前年度の地価はありません");
					}
					priceOfLand.setAcreage(Integer.parseInt(data[14]));
					priceOfLand.setNearestStation(data[33]);
					priceOfLand.setUsage(data[53]);
					priceOfLand.setNeighborhoodUsage(data[54]);

					// SQLにinsertする際にwhereで調べるようの住所を作成
					try {
						if (!startsWithDigit(data[10])) {
							String[] ad = split(data[10]);
							if (ad[0].isEmpty()) {
								priceOfLand.setAddressForSearch(ad[1]);
								System.out.println("ad[1]:" + ad[1]);
							} else {
								priceOfLand.setAddressForSearch(ad[0]);
								System.out.println("ad[0]:" + ad[0]);
							}
						} else {
							priceOfLand.setAddressForSearch(data[7].replaceAll("　", ""));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					priceOfLandList.add(priceOfLand);// カラム毎に別れた一行ずつOriginal型のリストに挿入
				}
				index++;// 行数を進める
			}
		} catch (IOException e) {
			System.out.println(index + "件目のファイルの読み込みに失敗");
		}
		System.out.println("確認:" + "小野路町字町田".lastIndexOf("字"));
		myBatch.insertToUsages(priceOfLandList);
		myBatch.insertToNearestStations(priceOfLandList);
		myBatch.insertToPriceOfLand(priceOfLandList);// 取得した情報をinsert
//		for (PriceOfLand kakuninn : priceOfLandList) {
//			System.out.println(kakuninn);
//		}
	}

	// 漢数字に変換するメソッド
	public static String convertToKanji(String input) {
		// 数字を漢数字に変換する処理を実装してください
		// 以下は一部の例を示しますが、必要に応じて追加の変換処理を行ってください
		input = input.replace("１０", "十");
		input = input.replace("１", "一");
		input = input.replace("２", "二");
		input = input.replace("３", "三");
		input = input.replace("４", "四");
		input = input.replace("５", "五");
		input = input.replace("６", "六");
		input = input.replace("７", "七");
		input = input.replace("８", "八");
		input = input.replace("９", "九");

		return input;
	}

	// 住所を分割するメソッド
	static String[] split(String str) throws Exception {
		System.out.println("--splitメソッド通過");
		String[] ad = new String[3];

		// 判定するパターンの生成準備
		// 半角数字・全角数字が一回以上または漢数字が一回以上（番地の先頭）
		String p1 = "([0-9０-９]+|[一二三四五六七八九十百千万]+)+";

		// 半角数字・全角数字が一回以上または漢数字が一回以上またはハイフンなどの接続記号が０回以上（番地の中身）
		String p2 = "([0-9０-９]+|[一二三四五六七八九十百千万]+|(丁目|番地|番|号|-|‐|ー|−|の))*";

		// 半角数字・全角数字が一回以上または漢数字が一回以上または数字/号など番地の終わりに使われる文字が１回（番地の終わり）
		String p3 = "((字)+[0-9０-９]+|[一二三四五六七八九十百千万]+|(丁目|番地|番|号))";

		// ex)番地が2-6-6なら「2」がp1、「-6-」がp2、「6」がp3に当てはまります。

		// 正規表現
		Pattern p = Pattern.compile(p1 + p2 + p3);
		Matcher m = p.matcher(str);

		// 正規表現に該当する文字列があればその前後で住所を分割する
		if (m.find()) {
			ad[0] = str.substring(0, m.start());
			ad[1] = str.substring(m.start(), m.end());
			ad[2] = str.substring(m.end(), str.length());
			// 四万十3-6-1みたいな漢数字+数字の時のための処理
			// (３分割された住所の番地部分に、漢数字１回＋数字１回という正規表現に該当する文字列があれば分割する境を訂正する)

			// 漢数字１回＋数字１回
			String p4 = "[一二三四五六七八九十百千万][0-9０-９]";

			p = Pattern.compile(p4);
			m = p.matcher(ad[1]);
			if (m.find()) {
				String tmp = ad[1].substring(0, m.start());
				ad[0] = ad[0] + tmp;
				ad[1] = ad[1].substring(m.start(), ad[1].length());
			}

			// 3-4-5三井ビルみたいな数字+漢数字の時のための処理
			String p5 = "[0-9０-９][一二三四五六七八九十百千万]";
			p = Pattern.compile(p5);
			m = p.matcher(ad[1]);
			if (m.find()) {
				String tmp = ad[1].substring(m.start() + 1, ad[1].length());
				ad[2] = tmp + ad[2];
				ad[1] = ad[1].substring(0, m.start());
			}
		} else
			ad[0] = str;
		if (ad[1].indexOf("丁目") != -1) {

			ad[1] = ad[1].substring(0, ad[1].indexOf("丁目") + 2);
			ad[0] += convertToKanji(ad[1]);
		}
		if (3 <= ad[0].lastIndexOf("字") && ad[0].lastIndexOf("字") <= 7) {
			ad[0] = ad[0].substring(0, ad[0].lastIndexOf("字"));
			if (ad[0].equals("大字谷保")) {
				ad[0] = ad[0].replaceAll("大字谷保", "谷保");
			}
		}
		return ad;
	}

	// 住所が大文字の数字から始まっているかを確認する
	public static boolean startsWithDigit(String str) {
		return str.matches("[０-９].*");
	}

}
