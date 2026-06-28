package com.tuservicio.infrastructure.adapter;

import com.tuservicio.core.domain.Conciliation;
import com.tuservicio.core.port.ConciliationUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/conciliation")
public class
RestControllerAdapter {

    private final ConciliationUseCase conciliationUseCase;

    public RestControllerAdapter(ConciliationUseCase conciliationUseCase) {
        this.conciliationUseCase = conciliationUseCase;
    }

    @PostMapping("/reconcile")
    public ResponseEntity<Conciliation> reconcile() {
        Conciliation result = conciliationUseCase.reconcile();
        return ResponseEntity.ok(result);
    }
}
