import 'dart:convert';
import 'package:coop/models/cuenta.dart';
import 'package:coop/models/respuesta.dart';
import 'package:http/http.dart' as http;

class ServiciosDAO {

  static const String ser1 = "http://localhost:8080/BancaWebMovill/srv/movil/usuario";
  static const String ser2 ='http://localhost:8080/BancaWebMovill/srv/movil/actualizarContra';
  static const String bus = "http://localhost:8080/BancaWebMovill/srv/movil/create";
  static const String ser3 = "http://localhost:8080/BancaWebMovill/srv/movil/create";
  static const String ser4 = "http://localhost:8080/BancaWebMovill/srv/movil/create";

  static Future<Respuesta> login(username, password) async {
    http.Response response = await http.post(ser1, body: {
      "username": username,
      "password": password,
    });


  static Future<Respuesta> cambioClave(correo, contravieja, contranueva) async {
    http.Response response = await http.post(ser2, body: {
      "correo": correo,
      "contraAntigua": contravieja,
      "contraActual": contranueva,
    });
    Respuesta res = new Respuesta();
    res.codigo = json.decode(response.body)["codigo"];
    res.descripcion = json.decode(response.body)["descripcion"];
    return res;
  }

  static Future<Respuesta> busqueda(numeroCuenta) async {
    final response = await http.get(bus + "?numeroCuenta=$numeroCuenta");
    Respuesta res = new Respuesta();
    var cue = json.decode(response.body)["Cuenta"];
    if (cue != null) {
      Cuenta cuenta = Cuenta.fromJson(cue);
      res.codigo = json.decode(response.body)["codigo"];
      res.descripcion = json.decode(response.body)["descripcion"];
      res.cuenta = cuenta;
    } else {
      res.codigo = json.decode(response.body)["codigo"];
      res.descripcion = json.decode(response.body)["descripcion"];
    }
    return res;
  }

  static Future<Respuesta> transferencia(Map jsonTrans) async {
    var response = await http.post(ser3,
        body: json.encode(jsonTrans),
        headers: {"Content-Type": "application/json"});
    Respuesta res = new Respuesta();
    print(response.body);
    res.codigo = json.decode(response.body)["codigo"];
    res.descripcion = json.decode(response.body)["descripcion"];
    return res;
  }

  static Future<Respuesta> transferenciaExterna(Map jsonTrans) async {
    var response = await http.post(ser4,
        body: json.encode(jsonTrans),
        headers: {"Content-Type": "application/json"});
    Respuesta res = new Respuesta();
    print(response.body);
    res.codigo = json.decode(response.body)["codigo"];
    res.descripcion = json.decode(response.body)["descripcion"];
    return res;
  }
}
