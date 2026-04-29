import re
with open('Laporan/PPPL_Januarsyah akbar_535846.tex', 'r') as f:
    text = f.read()

start_str = r"%----------------------------------------------------------------------\n\\section{Latihan}"
end_str = r"% \chapter{Resources}"

start_match = re.search(start_str, text)
if not start_match:
    print("Start string not found.")
    exit(1)
start_idx = start_match.start()
end_idx = text.find(end_str)
if end_idx == -1:
    print("End string not found.")
    exit(1)

new_content = r"""%----------------------------------------------------------------------
\section{Implementasi Web Element Locators}
%----------------------------------------------------------------------

Berikut adalah penjelasan dan tahap praktikum untuk pembuatan dan eksekusi \textit{Automated Testing} terhadap interaksi aplikasi spesifik, yakni portal SauceDemo menggunakan berbagai tipe \textit{Web Element Locators}.

\subsection{Pengujian Login SauceDemo Sederhana (\texttt{SauceDemoLoginTest.java})}
Pada bagian ini, kita akan membuat kelas \texttt{SauceDemoLoginTest} untuk memverifikasi proses \textit{login} dengan mencari elemen-elemennya menggunakan locators spesifik seperti Name, XPath, dan Tag Name.

\subsubsection*{1. Mendefinisikan Package dan Import}
Langkah pertama adalah melakukan \textit{import library} yang dibutuhkan dari Selenium WebDriver dan JUnit 5:

\begin{lstlisting}[style=javastyle, caption=Import Dependencies]
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;

import static org.junit.jupiter.api.Assertions.*;
\end{lstlisting}

\subsubsection*{2. Persiapan WebDriver (Setup)}
Selanjutnya, kita mendefinisikan kelas pengujian dan melakukan persiapan awal menggunakan anotasi \texttt{@BeforeEach}. Inisialisasi \textit{browser} menggunakan \texttt{SafariDriver}.

\begin{lstlisting}[style=javastyle, caption=Inisialisasi Kelas dan Setup WebDriver]
public class SauceDemoLoginTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new SafariDriver();
    }
\end{lstlisting}

\subsubsection*{3. Helper Methods untuk Pencarian Elemen}
Untuk menghindari repetisi serta \textit{error} yang tak tertangani, dibuat helper method \texttt{find} untuk menyederhanakan pencarian elemen dan \texttt{isPresent} untuk mengecek apakah elemen muncul.

\begin{lstlisting}[style=javastyle, caption=Helper Method]
    private WebElement find(By locator) {
        return driver.findElement(locator);
    }

    private boolean isPresent(By locator) {
        try {
            return !driver.findElements(locator).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
\end{lstlisting}

\subsubsection*{4. Skenario Pengujian Login dengan Beragam Locator}
Skenario utama mengarahkan web driver ke laman SauceDemo dan menggunakan locators untuk melakukan interaksi. \texttt{By.xpath} digunakan untuk validasi teks header. \texttt{By.name} dipakai untuk input username. Sedangkan password ditemukan menggunakan \textit{XPath axes} (\texttt{following}). Terakhir, \texttt{By.tagName} diiterasikan untuk mencari tombol berjenis \texttt{submit}.

\begin{lstlisting}[style=javastyle, caption=Test Method Login Locator]
    @Test
    public void testLogin() {
        driver.get("https://www.saucedemo.com/");
        
        // 1) cek text "Swag Labs" ada menggunakan XPath (text node)
        By swagText = By.xpath("//*[text()='Swag Labs']");
        assertTrue(isPresent(swagText), "Swag Labs text should be present");

        // 2) cek field username ada (gunakan locator by NAME)
        By usernameBy = By.name("user-name");
        assertTrue(isPresent(usernameBy), "Username field should be present");
        
        WebElement usernameInput = find(usernameBy);
        usernameInput.clear();
        usernameInput.sendKeys("standard_user");

        // 3) cek field password menggunakan XPath dengan axes
        By passwordBy = By.xpath("//input[@name='user-name']/following::input[@type='password'][1]");
        assertTrue(isPresent(passwordBy), "Password field should be present");
        
        WebElement passwordInput = find(passwordBy);
        passwordInput.clear();
        passwordInput.sendKeys("secret_sauce");

        // 4) cek tombol login menggunakan TAG / collection approach
        java.util.List<WebElement> inputs = driver.findElements(By.tagName("input"));
        WebElement loginButton = null;
        for (WebElement el : inputs) {
            String type = el.getAttribute("type");
            if (type != null && (type.equalsIgnoreCase("submit") || type.equalsIgnoreCase("button"))) {
                loginButton = el;
                break;
            }
        }
        assertNotNull(loginButton, "Login button should be present");
        
        loginButton.click();

        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/inventory.html", currentUrl);
    }
\end{lstlisting}

\subsubsection*{5. Membersihkan WebDriver (Teardown)}
Bagian terakhir menggunakan anotasi \texttt{@AfterEach} ditujukan untuk mengakhiri sesi \textit{browser} secara aman:

\begin{lstlisting}[style=javastyle, caption=Penutupan Sesi Browser]
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
\end{lstlisting}

\subsubsection*{Hasil Test}
Berikut adalah tampilan interaksi sukses yang mengkonfirmasi \textit{locators} berhasil menjangkau komponen front-end aplikasi web tersebut.

\begin{figure}[H]
    \centering
    \includegraphics[width=0.9\textwidth]{screenshot step/testlogin.png} \\[0.5cm]
    \includegraphics[width=0.9\textwidth]{screenshot step/sauceinventory.png}
    \caption{Hasil Eksekusi Test dengan Implementasi Locators}
\end{figure}

"""

text = text[:start_idx] + new_content + text[end_idx:]

with open('Laporan/PPPL_Januarsyah akbar_535846.tex', 'w') as f:
    f.write(text)
print("SUCCESS!")
