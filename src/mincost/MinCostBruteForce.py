cost_a = 100
cost_b = 20
cost_c = 10

num_a = 5
num_b = 5
num_c = 5

energy_a = 5
energy_b = 3
energy_c = 2

target_energy = 15
min_cost = 1000000
found = 0

for i in range(num_a + 1):
    for j in range(num_b + 1):
        for k in range(num_c + 1):

            t = i * energy_a + j * energy_b + k * energy_c

            if t == target_energy:

                cost = i * cost_a + j * cost_b + k * cost_c;

                if min_cost > cost:
                    min_cost = cost
                    found = 1

if found == 1:
    print(min_cost)
else:
    print(-1)
