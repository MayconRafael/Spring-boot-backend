// package com.br.backend.usuario;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.web.client.TestRestTemplate;
// import org.springframework.http.HttpEntity;
// import org.springframework.http.HttpMethod;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.test.annotation.DirtiesContext;

// import com.br.backend.entity.Usuario;
// import com.jayway.jsonpath.DocumentContext;
// import com.jayway.jsonpath.JsonPath;


// import static org.assertj.core.api.Assertions.assertThat;

// import java.net.URI;


// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// class UsuarioApplicationTests {
    
//     @Autowired
//     TestRestTemplate restTemplate;

//     @Test
//     void deveRetornarTodosOsUsuarios() {
//         ResponseEntity<String> resposta = restTemplate
//                 .getForEntity("/usuario", String.class);
//         assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.OK);
//     }

//     @Test
//     @DirtiesContext
//     void deveCriarUmNovoUsuario() {

//         Usuario novoUsuario = new Usuario();
//         novoUsuario.setNome("Matheus");
//         novoUsuario.setSenha("12345678");

//         ResponseEntity<Void> resposta = restTemplate                
//                 .postForEntity("/usuario", novoUsuario, Void.class);
//         assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.CREATED);

//         URI localizacaoDoNovoUsuario = resposta.getHeaders().getLocation();
//         ResponseEntity<String> pegarResposta = restTemplate                
//                 .getForEntity(localizacaoDoNovoUsuario, String.class);
//         assertThat(pegarResposta.getStatusCode()).isEqualTo(HttpStatus.OK);

//         DocumentContext documentContext = JsonPath.parse(pegarResposta.getBody());
//         Number id = documentContext.read("$.id");
//         String nome = documentContext.read("$.nome");

//         assertThat(id).isNotNull();
//         assertThat(nome).isEqualTo("Matheus");
//     }

//     @Test
//     void deveRetornarUmUsuarioPorId() {
//         ResponseEntity<String> resposta = restTemplate                
//             .getForEntity("/usuario/1", String.class);
//         assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.OK);

//         DocumentContext documentContext = JsonPath.parse(resposta.getBody());
//         Number id = documentContext.read("$.id");
//         assertThat(id).isEqualTo(1);

//         String nome = documentContext.read("$.nome");
//         assertThat(nome).isEqualTo("Pedro");
//     }

//     @Test
//     void naoDeveRetornarUmUsuarioComUmIdDesconhecido() {
//         ResponseEntity<String> resposta = restTemplate                
//             .getForEntity("/usuario/1000", String.class);
//         assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//         assertThat(resposta.getBody()).isBlank();
//     }

//     @Test
//     @DirtiesContext
//     void deveAtualizarUmUsuarioExistente() {

//         Usuario novoUsuarioUpdate = new Usuario(null, null);
//         novoUsuarioUpdate.setId(null);
//         novoUsuarioUpdate.setNome("Augusto");
//         novoUsuarioUpdate.setSenha(null);

//         HttpEntity<Usuario> requisicao = new HttpEntity<>(novoUsuarioUpdate);
//         ResponseEntity<Void> resposta = restTemplate        
//                 .exchange("/usuario/1", HttpMethod.PUT, requisicao, Void.class);
//         assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

//         ResponseEntity<String> pegarResposta = restTemplate
//                 .getForEntity("/usuario/1", String.class);
//         assertThat(pegarResposta.getStatusCode()).isEqualTo(HttpStatus.OK);

//         DocumentContext documentContext = JsonPath.parse(pegarResposta.getBody());

//         Number id = documentContext.read("$.id");
//         String nome = documentContext.read("$.nome");

//         assertThat(id).isEqualTo(1);
//         assertThat(nome).isEqualTo("Augusto");
//     }

//     @Test
//     void naoDeveAtualizarUmUsuarioComIdQueNaoExiste() {
//         Usuario usuarioDesconhecido = new Usuario();
//         usuarioDesconhecido.setId(null);
//         usuarioDesconhecido.setNome("Maria");
//         usuarioDesconhecido.setSenha(null);

//         HttpEntity<Usuario> requisicao = new HttpEntity<>(usuarioDesconhecido);
//         ResponseEntity<Void> response = restTemplate
//                 .exchange("/usuario/10000", HttpMethod.PUT, requisicao, Void.class);
//         assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//     }

// }