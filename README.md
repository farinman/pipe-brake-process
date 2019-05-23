# Rohrbruch-Prozess Anleitung
## Voraussetzung
- Photn + Bread
- Buzzer
- Wassersensor
- Verbindungskabel
- Particle Account
- Azure Account
- Azure IoT Central vorbereite
- Maven

## Der Prozess kann zum Beispiel auf Heorku oder auf dem eigenen PC deployed werden. Beim eigenen PC muss man den Richtigen Port freigeben.

## Photon zusammen bauen
1. Im folgenden Code [pipe-break.c](/Photon%20Code/pipe-break.c) werden die Pins für die IoT-Komponenten beschrieben
2. Sobald gemacht, Photon starten

## Photon mit Azure IoT Central verbinden
1. Tutorial folgen und den Code in [pipe-break.c](/Photon%20Code/pipe-break.c) verwenden für Photon -> [Connect your Particle Photon Directly to Azure IoT Hub or IoT Central](https://github.com/gloveboxes/Connecting-Particle-Photon-to-Azure-IoT-Hub)

## Photon Template erstellen
1. Anhand folgendem Tutorial Template erstellen -> [Connect your Particle Photon Directly to Azure IoT Hub or IoT Central](https://docs.microsoft.com/en-us/azure/iot-central/howto-set-up-template)
2. Unter Measurements Event hinzufügen mit "Field Name" waterSensorStateChanged.
3. Unter Rules eine neue Regel hinzufügen
4. Action auswählen und dann Microsoft Flow
5. Dort ein Workflow erstellen der ausgeführt wird, wenn die Regel eintritt
6. Als nächstes muss ein neuer HTTP-Schritt hinzugefügt werden
7. Im URI feld muss die Adresse für die Camunda API mit den Prozess angaben eingetragen werden.
8. Im Body muss folgerndes sein: 
```json
{
  "variables": {
    "deviceId": {
      "value": "@{triggerBody()['device']['name']}<---das ist eine dynamische Variable von Microsoft Flow",
      "type": "String"
    },
    "ticketSystemMail": {
      "value": "fm3@boullosa.ch",
      "type": "String"
    },
    "sentFrom": {
      "value": "task@boullosa.ch",
      "type": "String"
    }
  },
  "startInstructions": [
    {
      "type": "startBeforeActivity",
      "activityId": "StartEvent_1"
    }
  ],
  "businessKey": ""
}
```

## Photon-Gerät in Azure IoT Central hinzufügen
1. Hier wieder folgnder Tutorial beachten -> [Connect your Particle Photon Directly to Azure IoT Hub or IoT Central](https://github.com/gloveboxes/Connecting-Particle-Photon-to-Azure-IoT-Hub)
2. Darauf beachten, dass der Name des Gerätes die ID von Particle hat.

## Testen
1. unter src/main/resources/data.sql den verwendeten Device hinzufügen oder einer der Anderen bearbeiten
2. In der CloseWaterDelegate-Klase den AccessToken mit dem auf dem Particle-Account austauschen
3. Applikation starten
4. Testen :)

## Bei Fragen 
Mich kontaktieren.
