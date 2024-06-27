import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


class Intern_task_2
{
private static final List<Integer> COMMON_PORTS = Arrays.asList(21, 22, 23, 25, 80, 110, 143, 443, 445, 3389);
private static final Map<String, String> LATEST_SOFTWARE_VERSIONS = new HashMap<>();
static
{
LATEST_SOFTWARE_VERSIONS.put("apache", "2.4.52");
LATEST_SOFTWARE_VERSIONS.put("nginx", "1.21.6");
LATEST_SOFTWARE_VERSIONS.put("mysql", "8.0.27");
}
public static void main(String[] args) 
{
Scanner scanner = new Scanner(System.in);
System.out.print("Enter the IP address or hostname to scan: ");
String target = scanner.nextLine();
System.out.println("Scanning for open ports...");
scanPorts(target);
System.out.println("Checking for outdated software versions...");
checkSoftwareVersions();
scanner.close();
}
private static void checkSoftwareVersions()
{
    Map<String, String> installedVersions = new HashMap<>();
    installedVersions.put("apache", "2.4.46");
    installedVersions.put("nginx", "1.18.0");
    installedVersions.put("mysql", "5.7.33");
    for (String software : installedVersions.keySet()) 
    {
        String installedVersion = installedVersions.get(software);
        String latestVersion = LATEST_SOFTWARE_VERSIONS.get(software);
        if (latestVersion != null && !installedVersion.equals(latestVersion))
        {
            System.out.println(software + " is outdated. Installed version: " + installedVersion + ", Latest version: " + latestVersion);
        } 
        else 
        {
            System.out.println(software + " is up-to-date.");
        }
    }
}

private static void scanPorts(String target) 
{
for (int port : COMMON_PORTS) 
{
try (Socket socket = new Socket()) 
{
socket.connect(new InetSocketAddress(target, port), 200);
System.out.println("Port " + port + " is open.");
} 
catch (IOException e) 
{
System.out.println("Port " + port + " is closed.");
}
}
}
}