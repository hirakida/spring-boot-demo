package com.example.config;

import net.greghaines.jesque.Job;
import net.greghaines.jesque.worker.Worker;
import net.greghaines.jesque.worker.WorkerEvent;
import net.greghaines.jesque.worker.WorkerListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WorkerListenerImpl implements WorkerListener {

    @Override
    public void onEvent(WorkerEvent event, Worker worker, String queue, Job job, Object runner,
                        Object result, Throwable t) {
//        log.info("[{}]", Thread.currentThread().getName());
        if (event == WorkerEvent.JOB_FAILURE || event == WorkerEvent.WORKER_ERROR) {
            log.error("event={} worker={} queue={} job={} runner={} result={}",
                      event, worker, queue, job, runner, result, t);
        } else if (event == WorkerEvent.WORKER_POLL) {
            log.debug("event=WORKER_POLL worker={} queue={} job={} runner={} result={}",
                      worker, queue, job, runner, result, t);
        } else {
            log.info("event={} worker={} queue={} job={} runner={} result={}",
                     event, worker, queue, job, runner, result, t);
        }
    }
}
