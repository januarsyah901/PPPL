import re

with open("Laporan/PPPL_Januarsyah akbar_535846.tex", "r") as f:
    content = f.read()

# Change title
content = content.replace("AUTOMATION TESTING", "AUTOMATION TESTING - WEB ELEMENT LOCATORS")

# Change Tujuan Praktikum
old_tujuan = r"\\begin\{enumerate\}.*?\\end\{enumerate\}"
new_tujuan = r"""\\begin{enumerate}
    \\item Mahasiswa mampu memahami berbagai teknik lokator elemen web menggunakan Selenium WebDriver.
    \\item Mahasiswa mampu mengimplementasikan pencarian elemen menggunakan berbagai tipe locators seperti Name, XPath, dan Tag Name.
\\end{enumerate}"""
content = re.sub(old_tujuan, new_tujuan, content, flags=re.DOTALL, count=1)

# Locate the beginning of chapter Hasil dan Pembahasan
hasil_pembahasan_idx = content.find(r"Hasil dan Pembahasan]{Hasil dan Pembahasan}")

# Find the start of resources chapter or conclusion
kesimpulan_idx = content.find(r"\section*{Repository}")

new_hasil = r"""
%---------------------------------------------------------------import re

with open("Laporan Web Element     content = f.read()

# Change title
content = content.replac--
# Change title
conteikucontent =penjel
# Change Tujuan Praktikum
old_tujuan = r"\\begin\{enumerate\}.*?\\end\{enumerate\}"
new_ inteold_tujuan = r"\\begin\{ ynew_tujuan = r"""\\begin{enumerate}
    \\item MahasiswaWe    \\item Mahasiswa mampu memahamPe    \\item Mahasiswa mampu mengimplementasikan pencarian elemen menggunakan berbagai tipe locators seua\\end{enumerate}"""
content = re.sub(old_tujuan, new_tujuan, content, flags=re.DOTALL, count=1)

# Locate the beginning of chapter Hpecontent = re.sub(o, 
# Locate the beginning of chapter Hasil dan Pembahasan
hasil_pembahasan_i}
Lhasil_pembahasan_idx = content.find(r"Hasil dan Pembay}
# Find the start of resources chapter or conclusion
kesimpulan_idx = content.findvaskesimpulan_idx = content.find(r"\section*{Repositot.
new_hasil = r"""
%-----------------------------------for%--------------rg
with open("Laporan Web Element     content = f.read()

# Change title
ca.s
# Change title
content = content.replac--
# Change mencontent = con.o# Change title
conteikucoarconteikuconteor# Change Tujuan Praktiitold_tujuan = r"\\begin\{d{new_ inteold_tujuan = r"\\begin\{ ynew_tujuan = r"""\\beup    \\item MahasiswaWe    \\item Mahasiswa mampu memahamPe    \\item sicontent = re.sub(old_tujuan, new_tujuan, content, flags=re.DOTALL, count=1)

# Locate the beginning of chapter Hpecontent = re.sub(o, 
# Locate the beginning of chapter Hasil dan s 
# Locate the beginning of chapter Hpecontent = re.sub(o, 
# Locate the bever# Locate the beginning of chapter Hasil dan Pembahasan
h  hasil_pembahasan_i}
Lhasil_pembahasan_idx = content.f

Lhasil_pembahasan_ H# Find the start of resources chapter or conclusion
kes rkesimpulan_idx = content.findvaskesimpulan_idx = c},new_hasil = r"""
%-----------------------------------for%--------------rg
with opentt%--------------tuwith open("Laporan Web Element     content = f.read()

yl
# Change title
ca.s
# Change title
content = contenlemca.s
# Changeoc# Cr)content = contu# Change mencontent = conatconteikucoarconteikuconteor# Change Tujut(
# Locate the beginning of chapter Hpecontent = re.sub(o, 
# Locate the beginning of chapter Hasil dan s 
# Locate the beginning of chapter Hpecontent = re.sub(o, 
# Locate the bever# Locate the beginning of chapter Hasil dan Pembahasan
h  hasil_pembahasan_i}
Lhasil_pembahasan_idriv# Locate the beginning of chapter Hasil dan s 
# Locate ak# Locate the beginning of chapter Hpecontent nt# Locate the bever# Locate the beginning of chapter Hasiinh  hasil_pembahasan_i}
Lhasil_pembahasan_idx = content.f

Lhasil_pembahesLhasil_pembahasan_idx).
Lhasil_pembahasan_ H# Find the iitkes rkesimpulan_idx = content.findvaskesimpulan_idx = c},new_hasil = rti%-----------------------------------for%--------------rg
with opentt%---blwith opentt%--------------tuwith open("Laporan Web Elemau
yl
# Change title
ca.s
# Change title
content = contenlemca.s
# Changeoc# Cr)co (t#xtca.s
# Change  # Cswcontent = conpa# Changeoc# Cr)contentab# Locate the beginning of chapter Hpecontent = re.sub(o, 
# Locate the beginning of chapter Hasil  c# Locate the beginning of chapter Hasil dan s 
# Locate By# Locate the beginning of chapter Hpecontent ss# Locate the bever# Locate the beginning of chapter Hasi ph  hasil_pembahasan_i}
Lhasil_pembahasan_idriv# Locate the beginning of
 Lhasil_pembahasan_idrcl# Locate ak# Locate the beginning of chapter Hpecontent nt# Locate t3)Lhasil_pembahasan_idx = content.f

Lhasil_pembahesLhasil_pembahasan_idx).
Lhasil_pembahasan_ H# Find the iitkes rkesimpulan_idx = contentss
Lhasil_pembahesLhasil_pembahasaisPLhasil_pembahasan_ H# Find the iitkeshowith opentt%---blwith opentt%--------------tuwith open("Laporan Web Elemau
yl
# Change title
ca.s
# Change title
content = contenlemca.s
# Changeoc# Cr)co (t#xtc  yl
# Change title
ca.s
# Change title
content = contenlemca.s
# Changeoc# j#vaca.s
# ChangebE# Cencontent = conri# Changeoc# Cr)co (t#xgN# Change  # Cswcontent = cEl# Locate the beginning of chapter Hasil  c# Locate the beginning of chapter Hasil dan s 
# Locate By# Locate tpe# Locate By# Locate the beginning of chapter Hpecontent ss# Locate the bever# Locate threLhasil_pembahasan_idriv# Locate the beginning of
 Lhasil_pembahasan_idrcl# Locate ak# Locate the beginning of chapter Hpecontent nt# Locaog Lhasil_pembahasan_idrcl# Locate ak# Locate the l
Lhasil_pembahesLhasil_pembahasan_idx).
Lhasil_pembahasan_ H# Find the iitkes rkesimpulan_idx = contentss
Lhasil_pembahesLhasitorLhasil_pembahasan_ H# Find the iitkesisLhasil_pembahesLhasil_pembahasaisPLhasil_pembahasan_ H# Find theiayl
# Change title
ca.s
# Change title
content = contenlemca.s
# Changeoc# Cr)co (t#xtc  yl
# Change title
ca.s
# Change title
content = contenlemcle# cca.s
# Changepa# Cescontent = con  # Changeoc# Cr)co (t#xvo# Change title
ca.s
# Chang(dca.s
# Change) # C  content = coner# Changeoc# j#vaca.s
# }# ChangebE# Cencont

# Locate By# Locate tpe# Locate By# Locate the beginning of chapter Hpecontent ss# Locate the bever# Locate threLhasil_pembahasan_idriv# Locate the beginning of
 Lhasil_
\ Lhasil_pembahasan_idrcl# Locate ak# Locate the beginning of chapter Hpecontent nt# Locaog Lhasil_pembahasan_idrcl# Locate ak# Locate the l
Lhasil_pembahesLhasreLhasil_pembahesLhasil_pembahasan_idx).
Lhasil_pembahasan_ H# Find the iitkes rkesimpulan_idx = contentss
Lhasil_pembahesLhasitorLhasil_pem cLhasil_pembahasan_ H# Find the iitkesasLhasil_pembahesLhasitorLhasil_pembahasan_ H# Find the iitkesisLhim# Change title
ca.s
# Change title
content = contenlemca.s
# Changeoc# Cr)co (t#xtc  yl
# Change title
ca.s
# Change title
contenewca.s
# Changeac# CPecontent = conPe# Changeoc# Cr)co (t#x("# Change title
ca.s
# Changarca.s
# Change "# C acontent = conit# Changepa# Cescontent = conceca.s
# Chang(dca.s
# Change) # C  conte cat Laporan/PPPL_Januarsyah\ akbar_535846.tex
 ^C
 EOF
