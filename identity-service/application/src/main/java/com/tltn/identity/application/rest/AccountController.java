package com.tltn.identity.application.rest;

import com.tltn.identity.application.usecase.DeactivateAccountUseCase;
import com.tltn.identity.application.usecase.SoftDeleteAccountUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final DeactivateAccountUseCase deactivateUseCase;
    private final SoftDeleteAccountUseCase softDeleteUseCase;

    public AccountController(
            DeactivateAccountUseCase deactivateUseCase,
            SoftDeleteAccountUseCase softDeleteUseCase) {
        this.deactivateUseCase = deactivateUseCase;
        this.softDeleteUseCase = softDeleteUseCase;
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable UUID id) {
        deactivateUseCase.execute(new DeactivateAccountUseCase.DeactivateCommand(id));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDelete(@PathVariable UUID id) {
        softDeleteUseCase.execute(new SoftDeleteAccountUseCase.SoftDeleteCommand(id));
        return ResponseEntity.noContent().build();
    }
}
