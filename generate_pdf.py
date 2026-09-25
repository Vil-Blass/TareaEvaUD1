from reportlab.pdfgen import canvas
from reportlab.lib.pagesizes import letter
from pathlib import Path

output = Path(r"C:\Users\jeffr\Desktop\DAM\Acceso_A_Datos\TareaEvaUD1\resumen_proyecto.pdf")

texto = [
    "Proyecto DAM - Acceso a Datos",
    "",
    "Resumen del chat",
    "",
    "- Se revisó el proyecto Java y se corrigió el error principal en la clase DatosInventario.",
    "- Se validó la compilación del proyecto con javac.",
    "- Se comprobó que la creación del inventario y la consulta por categoría funcionan.",
    "- Se dejó la clase App como punto de entrada para ejecutar la aplicación.",
    "- Se probó la opción 2 del menú: listar equipos por categoría.",
    "- Se creó una estructura MVC temporal y luego se eliminó para dejar el proyecto en versión original.",
    "- Se verificó que el proyecto vuelve a funcionar correctamente con la versión final sin MVC.",
    "",
    "Comandos verificados:",
    "1. javac -d out (Get-ChildItem -Path src -Filter *.java | ForEach-Object { $_.FullName })",
    "2. java -cp out App 2 \"Categoría 1\"",
    "",
    "Salida verificada:",
    "-----------------------------------",
    "ID: 1",
    "Código: 12345678",
    "Nombre: Equipo 1",
    "Categoría: Categoría 1",
    "Stock: 10",
    "Precio: 19.99",
    "-----------------------------------",
    "Total de equipos encontrados: 1",
    "",
    "Conclusión:",
    "El proyecto está funcionando correctamente en consola y la opción de listar por categoría responde bien.",
]

c = canvas.Canvas(str(output), pagesize=letter)
c.setTitle("Resumen del proyecto")
c.setFont("Helvetica-Bold", 18)
c.drawString(72, 760, "Proyecto DAM - Acceso a Datos")
c.setFont("Helvetica", 11)
y = 730
for line in texto:
    if y < 60:
        c.showPage()
        y = 760
    c.drawString(72, y, line)
    y -= 18

c.save()
print(f"PDF generado: {output}")
