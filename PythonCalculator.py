import re
import tkinter
from tkinter.ttk import PanedWindow

class PyCalculator:
    calculation =''
    entryText=''

    def __init__(self):
        window = tkinter.Tk()
        window.title("Python-Calculator")
        window.geometry("390x560")
        window.resizable(False, False)
        window.config(bg='grey')

        window.iconbitmap("C:/Users/Teriq/OneDrive/Documents/Programs/Python/Graphics/Calculator App/icon.ico")

        PyCalculator.entryText = tkinter.StringVar()

        enter=tkinter.Entry(window, textvariable=PyCalculator.entryText, state='disable',width= 20, font=('Ariel', 24)).place(x=12, y=10, height= 45)
        clear= tkinter.Button(window, text= "clear", width=11,height=3, command=PyCalculator.clear).place(x=50, y=65)
        delete= tkinter.Button(window, text= "delete", width=11,height=3, command=PyCalculator.delete).place(x=250, y=65)

        buttonPanel = PanedWindow()
        buttonPanel.place(x=10, y=130)
        PyCalculator.setButtons(buttonPanel, window)

        window.mainloop()
        return
    
    def setButtons(buttonPanel, window):
        numButtons = []
        funcButtons= []
        x=1
        y= 132
        for i in range(10):
            i= tkinter.Button(buttonPanel, text= i, width= 11, height= 6)
            numButtons.append(i)

        for i in range(6):
            if i<4:
                i= tkinter.Button(window, width= 11, height= 6)
            else: i= tkinter.Button(buttonPanel, width= 11, height= 6)
            funcButtons.append(i) 
        funcButtons[0].config(text="+")
        funcButtons[1].config(text="*")
        funcButtons[2].config(text="-")
        funcButtons[3].config(text="/")
        funcButtons[4].config(text=".", command=lambda button=funcButtons[4]: PyCalculator.onClick(button))
        funcButtons[5].config(text="=", command=PyCalculator.calculate)

        for i in range(4):
            funcButtons[i].place(x= 282, y= y)
            funcButtons[i].config(command=lambda button=funcButtons[i]: PyCalculator.onClick(button))
            y=y+102

        for i in range(3):
            for j in range(3):
                numButtons[x].grid(padx=1,pady=1,row=i,column=j)
                numButtons[x].config(command=lambda button=numButtons[x]: PyCalculator.onClick(button))
                x=x+1
        numButtons[0].grid(padx=1,pady=1,row=4,column=0)
        numButtons[0].config(command=lambda button=numButtons[0]: PyCalculator.onClick(button))
        funcButtons[4].grid(padx=1,pady=1,row=4,column=1)
        funcButtons[5].grid(padx=1,pady=1,row=4,column=2)
        return

    def clear():
        PyCalculator.calculation=''
        PyCalculator.entryText.set(PyCalculator.calculation)
    def delete():
        PyCalculator.calculation=PyCalculator.calculation[0:(len(PyCalculator.calculation)-1)]
        PyCalculator.entryText.set(PyCalculator.calculation)
    def onClick(button):
        PyCalculator.calculation= PyCalculator.calculation+str(button.cget('text'))
        PyCalculator.entryText.set(PyCalculator.calculation)

    def calculate():
        if PyCalculator.calculation.__contains__('++') or PyCalculator.calculation.__contains__('..') or PyCalculator.calculation == '':
            answer = 0
        else:
            numbers = []
            operators =[]
            numbers = re.split('[*/+-]+',PyCalculator.calculation)
            operators = re.split('[.0-9]+', PyCalculator.calculation)
            answer = float(numbers[0])
            for i in range(len(numbers)):
                if operators[i] == "+":
                    answer = answer+float(numbers[i])
                elif operators[i] == "-":
                    answer = answer-float(numbers[i])
                elif operators[i] == "*":
                    answer = answer*float(numbers[i])
                elif operators[i] == "/":
                    answer = answer/float(numbers[i])
        PyCalculator.calculation = str(answer)
        PyCalculator.entryText.set(PyCalculator.calculation)
        return

calculator = PyCalculator()