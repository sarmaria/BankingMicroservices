package com.banking.accounts.controller;

import com.banking.accounts.dto.AccountsContactInfoDto;
import com.banking.accounts.dto.CustomerDto;
import com.banking.accounts.dto.ErrorResponseDto;
import com.banking.accounts.dto.ResponseDto;
import com.banking.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Validated
@Tag(name = "Accounts Rest API")
public class AccountsController {

    private final IAccountsService accountsService;
    private final Environment environment;
    private final AccountsContactInfoDto accountsContactInfoDto;

    @Value("${build.info}")
    private String buildInfo;

    public AccountsController(IAccountsService accountsService, Environment environment, AccountsContactInfoDto accountsContactInfoDto) {
        this.accountsService = accountsService;
        this.environment = environment;
        this.accountsContactInfoDto = accountsContactInfoDto;
    }

    @Operation(summary = "Create Account Rest API")
    @ApiResponse(responseCode = "201")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody CustomerDto customerDto) {
        accountsService.createNewAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto("Account Created Successfully", HttpStatus.CREATED));
    }

    @Operation(summary = "Fetch Account Rest API")
    @ApiResponse(responseCode = "200")
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetch(@Pattern(regexp = "^\\d{10,12}$", message = "Mobile number must have 10 digits") @RequestParam String mobileNumber) {
        CustomerDto customerDto = accountsService.getAccountDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(summary = "Update Account Rest API")
    @ApiResponses({@ApiResponse(responseCode = "202"),
            @ApiResponse(responseCode = "500",
            content=@Content(schema = @Schema(implementation = ErrorResponseDto.class)))})
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> update(@RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountsService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto("Account Updated", HttpStatus.ACCEPTED));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto("Error Occurred.", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @Operation(summary = "Delete Account Rest API")
    @ApiResponses({@ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "500",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class))),})
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> delete(@Pattern(regexp = "^\\d{10,12}$", message = "Mobile number must have 10 digits") @RequestParam String mobileNumber) {
        boolean isDeleted = accountsService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto("Account Deleted", HttpStatus.OK));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDto("Error Occurred.", HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Operation(summary = "Fetch Build Info Rest API")
    @ApiResponse(responseCode = "200")
    @GetMapping("/build-info")
    public ResponseEntity<String> buildInfo(){
        return ResponseEntity.status(HttpStatus.OK).body(buildInfo);
    }

    @Operation(summary = "Fetch Java Home Info  Rest API")
    @ApiResponse(responseCode = "200")
    @GetMapping("/java-info")
    public ResponseEntity<String> javaInfo(){
        return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("JAVA_HOME"));
    }

    @Operation(summary = "Fetch Contact Info Rest API")
    @ApiResponse(responseCode = "200")
    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto> contactInfo(){
        return ResponseEntity.status(HttpStatus.OK).body(accountsContactInfoDto);
    }
}
