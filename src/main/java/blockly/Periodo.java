package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;


@CronapiMetaData(type = "blockly")
@CronappSecurity
public class Periodo {

public static final int TIMEOUT = 300;

/**
 *
 * @param Entidade<app.entity.Periodo>
 *
 * @author Ícaro Antunes
 * @since 06/12/2023, 15:11:23
 *
 */
public static Var automatizaUser(@ParamMetaData(description = "Entidade", id = "dd36eefc") Var Entidade) throws Exception {
 return new Callable<Var>() {

   public Var call() throws Exception {
    return
cronapi.list.Operations.getFirst((
cronapi.database.Operations.query(Var.valueOf("app.entity.User"),Var.valueOf("select \n	u.id \nfrom \n	User u  \nwhere \n	u.normalizedUserName = :normalizedUserName"),Var.valueOf("normalizedUserName",
cronapi.text.Operations.normalize(
cronapi.util.Operations.getCurrentUserName())))));
   }
 }.call();
}

/**
 *
 * @param Entidade<app.entity.Periodo>
 *
 * @author Ícaro Antunes
 * @since 06/12/2023, 15:11:23
 *
 */
public static Var validaAntesDeInserir(@ParamMetaData(description = "Entidade", id = "be8f3e29") Var Entidade) throws Exception {
 return new Callable<Var>() {

   private Var periodoGerado = Var.VAR_NULL;
   private Var consultaPeriodo = Var.VAR_NULL;
   private Var peridobancoativotrue = Var.VAR_NULL;
   private Var objetoperiododataexistente = Var.VAR_NULL;

   public Var call() throws Exception {
    periodoGerado =
    Var.VAR_NULL;
    if (
    Var.valueOf(
    Var.valueOf(
    cronapi.object.Operations.getObjectField(Entidade,
    Var.valueOf("per")).equals(
    Var.valueOf("1"))).getObjectAsBoolean() ||
    Var.valueOf(
    cronapi.object.Operations.getObjectField(Entidade,
    Var.valueOf("per")).equals(
    Var.valueOf("2"))).getObjectAsBoolean()).getObjectAsBoolean()) {
        periodoGerado =
        Var.valueOf(
        cronapi.conversion.Operations.toString(
        cronapi.object.Operations.getObjectField(Entidade,
        Var.valueOf("per"))).getObjectAsString() +
        Var.valueOf("/").getObjectAsString() +
        cronapi.dateTime.Operations.getYear(
        cronapi.object.Operations.getObjectField(Entidade,
        Var.valueOf("dataFinal"))).getObjectAsString());
        cronapi.database.Operations.updateField(Entidade,
        Var.valueOf("per"), periodoGerado);
    } else {
        cronapi.util.Operations.throwException(
        Var.valueOf("Periodo só pode ser 1 ou 2"));
    }
    consultaPeriodo =
    cronapi.database.Operations.query(Var.valueOf("app.entity.Periodo"),Var.valueOf("select \n	p \nfrom \n	Periodo p  \nwhere \n	p.per = :per"),Var.valueOf("per",periodoGerado));
    if (
    cronapi.database.Operations.hasElement(consultaPeriodo).getObjectAsBoolean()) {
        cronapi.util.Operations.throwException(
        Var.valueOf(
        Var.valueOf("Período ").getObjectAsString() +
        periodoGerado.getObjectAsString() +
        Var.valueOf(" já existe. Tente outro.").getObjectAsString()));
    }
    peridobancoativotrue =
    cronapi.database.Operations.query(Var.valueOf("app.entity.Periodo"),Var.valueOf("select \n	p \nfrom \n	Periodo p  \nwhere \n	p.ativo = true"));
    if (
    Var.valueOf(
    cronapi.database.Operations.getField(peridobancoativotrue,
    Var.valueOf("this[0].ativo")).getObjectAsBoolean() &&
    cronapi.object.Operations.getObjectField(Entidade,
    Var.valueOf("ativo")).getObjectAsBoolean()).getObjectAsBoolean()) {
        cronapi.util.Operations.throwException(
        Var.valueOf(
        Var.valueOf("Só pode-se ter um período ativo por vez. Desmarque.").getObjectAsString()));
    }
    objetoperiododataexistente =
    cronapi.database.Operations.query(Var.valueOf("app"),Var.valueOf("SELECT *\nFROM Periodo p\nWHERE :dataInicial BETWEEN p.dataInicial AND p.dataFinal\n   OR :dataFinal BETWEEN p.dataInicial AND p.dataFinal;"),Var.valueOf("dataInicial",
    cronapi.object.Operations.getObjectField(Entidade,
    Var.valueOf("dataInicial"))),Var.valueOf("dataFinal",
    cronapi.object.Operations.getObjectField(Entidade,
    Var.valueOf("dataFinal"))));
    if (
    cronapi.database.Operations.hasElement(objetoperiododataexistente).getObjectAsBoolean()) {
        cronapi.util.Operations.throwException(
        Var.valueOf(
        Var.valueOf("Data já está sendo usada").getObjectAsString()));
    }
    return Var.VAR_NULL;
   }
 }.call();
}

}

