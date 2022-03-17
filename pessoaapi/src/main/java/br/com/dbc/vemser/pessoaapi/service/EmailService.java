package br.com.dbc.vemser.pessoaapi.service;

import java.io.File;
import java.io.IOException;
//import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import br.com.dbc.vemser.pessoaapi.dto.ContatoDTO;
import br.com.dbc.vemser.pessoaapi.dto.EnderecoDTO;
import br.com.dbc.vemser.pessoaapi.dto.PessoaDTO;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService { 

	
    private final freemarker.template.Configuration fmConfiguration;
    
    private static final String MAIL_TO = "joaovjcode@gmail.com";

    @Value("${spring.mail.username}")
    private String from;
    private final JavaMailSender emailSender;
	private File dirPath = new File("src/main/resources/templates");
    
    
    public void sendEmail(PessoaDTO pessoaDTO, String type) {
    	MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(MAIL_TO);
            mimeMessageHelper.setSubject("TESTE");
            mimeMessageHelper.setText(geContentFromTemplate(pessoaDTO, type), true);

            emailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
    
    public String geContentFromTemplate(PessoaDTO pessoaDTO, String type) throws IOException, TemplateException {
        Map<String, Object> dados = new HashMap<>();
        Template template = null;
        dados.put("name", pessoaDTO.getNome());
        fmConfiguration.setDirectoryForTemplateLoading(dirPath);
        if(type.equalsIgnoreCase("create")) {
        	template = fmConfiguration.getTemplate("/emailpessoacreate-template.ftl");
        }else if(type.equalsIgnoreCase("update")){
        	template = fmConfiguration.getTemplate("/emailpessoaupdate-template.ftl");
        }else if(type.equalsIgnoreCase("delete")){
        	template = fmConfiguration.getTemplate("/emailpessoadelete-template.ftl");
        }
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
        return html;
    }
    
    public void sendEmail(EnderecoDTO enderecoDTO, PessoaDTO pessoaDTO, String type) {
    	MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(MAIL_TO);
            mimeMessageHelper.setSubject("TESTE");
            mimeMessageHelper.setText(geContentFromTemplate(enderecoDTO, pessoaDTO, type), true);

            emailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
    
    public String geContentFromTemplate(EnderecoDTO enderecoDTO, PessoaDTO pessoaDTO, String type) throws IOException, TemplateException {
        Map<String, Object> dados = new HashMap<>();
        dados.put("name", pessoaDTO.getNome());
        fmConfiguration.setDirectoryForTemplateLoading(dirPath);
        Template template = null;
        if(type.equalsIgnoreCase("create")) {
        	template = fmConfiguration.getTemplate("/emailenderecocreate-template.ftl");
        	dados.put("endereco", enderecoDTO.getLogadouro());
        }else if(type.equalsIgnoreCase("update")){
        	template = fmConfiguration.getTemplate("/emailenderecoupdate-template.ftl");
        }else if(type.equalsIgnoreCase("delete")){
        	dados.put("endereco", enderecoDTO.getLogadouro());
        	template = fmConfiguration.getTemplate("/emailenderecodelete-template.ftl");
        }
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
        return html;
    }
    
    public String geContentFromTemplate(ContatoDTO contatoDTO, PessoaDTO pessoaDTO, String path) throws IOException, TemplateException {
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", pessoaDTO.getNome());
        fmConfiguration.setDirectoryForTemplateLoading(dirPath);
        Template template = fmConfiguration.getTemplate(path);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
        return html;
    }
}
