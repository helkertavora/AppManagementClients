package br.com.unifor.AppManagementClients.constants;



/**
 * Muito importante a manutencao dessa classe.<BR/>
 * Os valores das constantes devem corresponder EXATAMENTE ao c�digo do modulo
 * cadastrado no sistema de seguranca.<BR/>
 * A inclusao de um novo modulo de seguranca no istema de seguranca tamb�m deve
 * ser incluido nessa classe com seu respectivo codigo. Alem de ter que ser
 * adicionado um get na classe {@link SegurancaController}. <BR/>
 * <BR/>
 * Essa classe tambem eh necessaria na classe {@link SegurancaController} para
 * a busca das permissoes do usuario
 */
public final class Constants {

	
	
	
	
	
	public static final String NOME_SISTEMA = "SISTEMA DE GERENCIAMENTO DE CLIENTE";
	public final static String MSG_SUCESSO = "Opera��o efetuada com sucesso.";
	public final static String MSG_ERRO = "Erro na opera��o, contacte o administrador do sistema.";
	public final static String MSG_NENHUM_REGISTRO = "Nenhum Registro foi localizado.";
	public final static String SLG_SISTEMA = "SGC";
	
}