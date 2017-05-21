package noppes.mpm.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import noppes.mpm.LogWriter;




































public class AnalyticsTracking
  extends Thread
{
  public static void sendData(final EntityPlayer player, final String event, final String data)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          UUID uuid = player.getUniqueID();
          






          String analyticsPostData = "v=" + URLEncoder.encode("1", "UTF-8") + "&tid=" + URLEncoder.encode("UA-29079943-4", "UTF-8") + "&cid=" + URLEncoder.encode(uuid.toString(), "UTF-8") + "&t=" + URLEncoder.encode("event", "UTF-8") + "&ec=" + URLEncoder.encode("moreplayermodels 1.7", "UTF-8") + "&ea=" + URLEncoder.encode(event, "UTF-8") + "&el=" + URLEncoder.encode(data, "UTF-8");
          String postDataType = "application/x-www-form-urlencoded";
          
          URL url = new URL("http://www.google-analytics.com/collect");
          HttpURLConnection connection = (HttpURLConnection)url.openConnection();
          connection.setConnectTimeout(10000);
          connection.setReadTimeout(10000);
          connection.setDoOutput(true);
          connection.setUseCaches(false);
          connection.setDoOutput(true);
          connection.setRequestMethod("POST");
          connection.setRequestProperty("Content-Type", postDataType);
          connection.setRequestProperty("Content-Length", String.valueOf(analyticsPostData.length()));
          OutputStream dataOutput = connection.getOutputStream();
          dataOutput.write(analyticsPostData.getBytes());
          dataOutput.close();
          connection.getInputStream().close();
        }
        catch (IOException e) {
          LogWriter.except(e);
        }
      }
    })
    

































      .start();
  }
}
