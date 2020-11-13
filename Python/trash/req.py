import requests as rq 

r = rq.get("https://edufpmi.bsu.by/login/index.php")
print(r.text)

with open("qwe.html", "w") as file:
    file.write(r.text)
